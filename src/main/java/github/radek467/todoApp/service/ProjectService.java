package github.radek467.todoApp.service;

import github.radek467.todoApp.TaskConfigurationProperties;
import github.radek467.todoApp.model.*;
import github.radek467.todoApp.model.projection.GroupReadModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
class ProjectService {
    ProjectRepository projectRepository;
    TaskGroupRepository groupRepository;
    TaskConfigurationProperties configurationProperties;

    public ProjectService(ProjectRepository projectRepository, TaskGroupRepository groupRepository, TaskConfigurationProperties configurationProperties) {
        this.projectRepository = projectRepository;
        this.groupRepository = groupRepository;
        this.configurationProperties = configurationProperties;
    }

    public List<Project> readAll(){
        return projectRepository.findAll();
    }

    public Project createProject(Project project){
        return projectRepository.save(project);
    }

    public GroupReadModel createGroup (Integer id, LocalDateTime deadline) {
        if (configurationProperties.getTemplate().isAllowMultipleTask() == false && groupRepository.existsByDoneIsFalseAndProject_Id(id) == true) {
            throw new IllegalStateException("Cannot create group. Exist undone group");
        }

        TaskGroup resultToReturn = projectRepository.findById(id).map(project -> {
                    var result = new TaskGroup();
                    result.setDescription(project.getDescription());
                    result.setTasks(project.getSteps().stream()
                            .map(projectStep -> new Task(
                                    projectStep.getDescription(),
                                    deadline.plusDays(projectStep.getDaysToDeadline())))
                            .collect(Collectors.toSet()));
                    result.setProject(project);
                    return groupRepository.save(result);
                }
        ).orElseThrow(() -> new IllegalArgumentException("Project with this id isn't exist"));
        return new GroupReadModel(resultToReturn);
    }
}
