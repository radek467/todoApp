package github.radek467.todoApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Task extends BaseTaskDataEntity{

    private LocalDateTime deadline;
    @Embedded
    private Audit audit = new Audit();

    @ManyToOne
    @JoinColumn(name = "task_group_id")
    TaskGroup group;

    public Task(){}

    public Task (String description, LocalDateTime deadline){
        this.description = description;
        this.deadline = deadline;
    }

    public void updateFrom(final Task source)
    {
        super.setDescription(source.getDescription());
        super.setDone(source.isDone());
        deadline = source.deadline;
    }


    public LocalDateTime getDeadline() { return deadline; }

    void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }
}

