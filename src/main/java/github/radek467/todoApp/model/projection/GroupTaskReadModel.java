package github.radek467.todoApp.model.projection;

import github.radek467.todoApp.model.Task;

import java.time.LocalDateTime;

public class GroupTaskReadModel {
    private String description;
    private boolean done;
    private LocalDateTime deadline;

    public GroupTaskReadModel(Task source){
        this.description = source.getDescription();
        this.deadline = source.getDeadline();
        this.done = source.isDone();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
