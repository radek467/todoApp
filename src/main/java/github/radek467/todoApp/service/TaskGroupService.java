package github.radek467.todoApp.service;

import github.radek467.todoApp.model.Task;
import github.radek467.todoApp.model.TaskGroup;
import github.radek467.todoApp.model.TaskGroupRepository;
import github.radek467.todoApp.model.TaskRepository;
import github.radek467.todoApp.model.projection.GroupReadModel;
import github.radek467.todoApp.model.projection.GroupTaskReadModel;
import github.radek467.todoApp.model.projection.GroupWriteModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class TaskGroupService {
    TaskGroupRepository repository;
    TaskRepository taskRepository;

    TaskGroupService(TaskGroupRepository repository, TaskRepository taskRepository){
        this.taskRepository = taskRepository;
        this.repository = repository;
    }

    public List<GroupReadModel> readAll()
    {
        return repository.findAll().stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }

    public GroupReadModel createGroup (GroupWriteModel source)
    {
        return new GroupReadModel(repository.save(source.toGroup()));
    }

    public void toggleGroup(Integer id){
        if(repository.existsByDoneIsFalseAndProject_Id(id) == true){
            throw new IllegalStateException("All tasks aren't done");
        }
        TaskGroup taskGroup = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Group with this id isn't exist"));
        taskGroup.setDone(!taskGroup.isDone());
    }
}
