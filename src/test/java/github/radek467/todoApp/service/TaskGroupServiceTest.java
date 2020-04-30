package github.radek467.todoApp.service;

import github.radek467.todoApp.model.TaskGroup;
import github.radek467.todoApp.model.TaskGroupRepository;
import github.radek467.todoApp.model.TaskRepository;
import jdk.jfr.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskGroupServiceTest {

    @DisplayName("Should throw illegal argument exception, exists undone task")
    @Test
    public void toggleGroup_existUndoneTask_throwIllegalArgumentException()
    {
        //given
        var taskRepository = mock(TaskRepository.class);
        when(taskRepository.existsByDoneIsFalseAndGroup_Id(anyInt())).thenReturn(true);
        var taskGroupRepository = mock(TaskGroupRepository.class);

        //service under test
        var serviceToTest = new TaskGroupService(taskGroupRepository, taskRepository);

        //when
        var result = catchThrowable(() -> serviceToTest.toggleGroup(1));

        //then
        assertThat(result).isInstanceOf(result.getClass()).hasMessageContaining("undone tasks");
    }

    @DisplayName("Should throw illegal argument exception, group with type id doesn't exist")
    @Test
    public void toggleGroup_dontExistGroup_throwIllegalArgumentException()
    {
        var taskRepository = mock(TaskRepository.class);
        when(taskRepository.existsByDoneIsFalseAndGroup_Id(anyInt())).thenReturn(false);
        var repository = mock(TaskGroupRepository.class);
        when(repository.findById(anyInt())).thenReturn(Optional.empty());

        //service under test
        var serviceToTest = new TaskGroupService(repository, taskRepository);

        //when
        var result = catchThrowable(() -> serviceToTest.toggleGroup(1));

        //then
        assertThat(result).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("isn't exist");
    }

    @DisplayName("Doesn't exist undone task, group exists, task group toggle successful")
    @Test
    public void toggleGroup_togglingSuccessful()
    {
        //given
        var taskRepository = mock(TaskRepository.class);
        when(taskRepository.existsByDoneIsFalseAndGroup_Id(anyInt())).thenReturn(false);
        var repository = mock(TaskGroupRepository.class);
        var taskGroup = new TaskGroup();
        boolean statusBeforeToggle = taskGroup.isDone();
        when(repository.findById(anyInt())).thenReturn(Optional.of(taskGroup));

        //service under test
        TaskGroupService serviceToTest = new TaskGroupService(repository, taskRepository);

        //when
        serviceToTest.toggleGroup(5);

        //then
        assertThat(statusBeforeToggle).isNotEqualTo(taskGroup.isDone());
    }
}