package github.radek467.todoApp.service;

import github.radek467.todoApp.TaskConfigurationProperties;
import github.radek467.todoApp.model.*;
import github.radek467.todoApp.model.projection.GroupReadModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class ProjectServiceTest {

//    @Test
//    @DisplayName("Should thrown illegal state exception, configuration allow only one undone group and these group exists")
//    void createGroup_MultipleTaskIsFalse_And_existsUndoneGroup_throwIllegalStateException() {
//
//        //given
//        var mockGroupRepository = mock(TaskGroupRepository.class);
//        when(mockGroupRepository.existsByDoneIsFalseAndProject_Id(anyInt())).thenReturn(true);
//        var mockTaskConfiguration = setConfig(false);
//        //var TaskGroupService = new TaskGroupService();
//
//        //service under test
//        var toTest = new ProjectService(,null, mockGroupRepository, mockTaskConfiguration);
//
//        //when
//        var result = catchThrowable(() -> toTest.createGroup(LocalDateTime.now(), 1));
//
//        //then
//
//        assertThat(result).isInstanceOf(IllegalStateException.class).hasMessageContaining("undone group");
//    }
//
//
//    @Test
//    @DisplayName("Should throw argument illegal exception, configuration allow many undone group, but these group doesnt exist, and project dont exist")
//    void createGroup_MultipleTaskIsTrue_And_projectNotExist_throwArgumentIllegalException()
//    {
//        //given
//        var mockProjectRepository = mock(ProjectRepository.class);
//        when(mockProjectRepository.findById(anyInt())).thenReturn(Optional.empty());
//
//        //service under test
//        var serviceToTest = new ProjectService(mockProjectRepository, null, setConfig(true));
//
//        //when
//        var result = catchThrowable(() -> serviceToTest.createGroup(LocalDateTime.now(), 2));
//
//        //then
//        assertThat(result).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Project with this id isn't exist");
//    }
//
//    @Test
//    @DisplayName("Should throw argument illegal exception. Undone group doesnt exist, but project dont exist")
//    void createGroup_notExistsUndoneGroup_and_projectNotExist_throwArgumentIllegalException()
//    {
//
//        //given
//        var mockProjectRepository = mock(ProjectRepository.class);
//        when(mockProjectRepository.findById(anyInt())).thenReturn(Optional.empty());
//        var mockGroupRepository = mock(TaskGroupRepository.class);
//        when(mockGroupRepository.existsByDoneIsFalseAndProject_Id(anyInt())).thenReturn(false);
//
//        //service under test
//        var serviceToTest = new ProjectService(mockProjectRepository, mockGroupRepository, setConfig(false));
//
//        //when
//        var result = catchThrowable(() -> serviceToTest.createGroup(LocalDateTime.now(), 5));
//
//        //then
//        assertThat(result).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Project with this id isn't exist");
//
//    }
//
//    @Test
//    @DisplayName("create group is successful")
//    void createGroup_configurationOk_projectExist_createGroup()
//    {
//        //given
//        LocalDateTime date = LocalDate.now().atStartOfDay();
//        var mockGroupRepository = mock(ProjectRepository.class);
//        var project = projectWith(Set.of(1, 2), "eg");
//        when(mockGroupRepository.findById(anyInt())).
//                thenReturn(Optional.of(project));
//        TaskConfigurationProperties config = setConfig(true);
//        MemoryGroupRepository memoryGroupRepository = new MemoryGroupRepository();
//        int size = memoryGroupRepository.count();
//
//        //service under test
//        ProjectService service = new ProjectService(mockGroupRepository, memoryGroupRepository, config);
//
//        //when
//        GroupReadModel toTest = service.createGroup(date, 1);
//
//        //then
//        assertThat(size + 1).isEqualTo(memoryGroupRepository.count());
//        assertThat(toTest.getDescription()).isEqualTo("eg");
//        assertThat(toTest.getDeadline()).isEqualTo(date.plusDays(1));
//        assertThat(toTest.getTasks()).allMatch(task -> task.getDescription().equals("test"));
//
//    }
//
//    Project projectWith(Set<Integer> daysToDeadline, String description)
//    {
//        var result = mock(Project.class);
//        when(result.getDescription()).thenReturn(description);
//        Set<ProjectStep> steps = daysToDeadline.stream()
//                .map(days -> {
//                    var step = mock(ProjectStep.class);
//                    when(step.getDescription()).thenReturn("test");
//                    when(step.getDaysToDeadline()).thenReturn(days);
//                    return step;
//                }).collect(Collectors.toSet());
//        when(result.getSteps()).thenReturn(steps);
//        return result;
//    }
//
//    private TaskConfigurationProperties setConfig(final boolean status) {
//        var mockTemplate = mock(TaskConfigurationProperties.Template.class);
//        when(mockTemplate.isAllowMultipleTask()).thenReturn(status);
//        var mockTaskConfiguration = mock(TaskConfigurationProperties.class);
//        when(mockTaskConfiguration.getTemplate()).thenReturn(mockTemplate);
//        return mockTaskConfiguration;
//    }
//
//
//    private static class MemoryGroupRepository implements TaskGroupRepository
//    {
//
//
//        Map<Integer, TaskGroup> repository = new HashMap<>();
//        int index = 0;
//
//        public int count()
//        {
//            return repository.values().size();
//        }
//        @Override
//        public List<TaskGroup> findAll() {
//            return repository.values().stream().collect(Collectors.toList());
//        }
//
//        @Override
//        public Optional<TaskGroup> findById(Integer id) {
//            return Optional.ofNullable(repository.get(id));
//        }
//
//        @Override
//        public boolean existsByDoneIsFalseAndProject_Id(Integer id) {
//            return repository.values().stream()
//                    .filter(taskGroup -> taskGroup.isDone() == false)
//                    .filter(taskGroup -> taskGroup.getProject().getId() == id && taskGroup.getProject() != null)
//                    .findAny()
//                    .isEmpty();
//        }
//
//        @Override
//        public TaskGroup save(final TaskGroup entity) {
//            if(entity.getId() == 0){
//                try{
//                    var field = TaskGroup.class.getSuperclass().getDeclaredField("id");
//                    field.setAccessible(true);
//                    field.set(entity, ++index);
//                }catch (NoSuchFieldException | IllegalAccessException ex){
//                    throw new RuntimeException(ex);
//                }
//            }
//            repository.put(entity.getId(), entity);
//            return entity;
//        }
//    }
}