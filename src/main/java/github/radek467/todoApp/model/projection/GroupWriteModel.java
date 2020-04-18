package github.radek467.todoApp.model.projection;


import github.radek467.todoApp.model.TaskGroup;

import java.util.Set;
import java.util.stream.Collectors;

public class GroupWriteModel {
    private String description;
    private Set<GroupTaskWriteModel> tasks;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<GroupTaskWriteModel> getTasks() {
        return tasks;
    }

    public void setTasks(Set<GroupTaskWriteModel> tasks) {
        this.tasks = tasks;
    }

    public TaskGroup toGroup(){
        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setDescription(this.description);
        taskGroup.setTasks(this.tasks.stream()
                                .map(GroupTaskWriteModel::toTask)
                                .collect(Collectors.toSet()));
        return taskGroup;
    }
}
