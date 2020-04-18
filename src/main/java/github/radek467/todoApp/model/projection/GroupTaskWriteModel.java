package github.radek467.todoApp.model.projection;

import github.radek467.todoApp.model.Task;
import github.radek467.todoApp.model.TaskRepository;

import java.time.LocalDateTime;

public class GroupTaskWriteModel {
    private String description;
    private LocalDateTime deadline;

    TaskRepository taskRepository;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Task toTask (){
        return new Task(description, deadline);
    }
}
