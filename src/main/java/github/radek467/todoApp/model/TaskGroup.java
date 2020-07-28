package github.radek467.todoApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
    @Table (name = "task_groups")
    public class TaskGroup extends BaseTaskDataEntity {

        @Embedded
        private Audit audit = new Audit();

        @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "group")
        private Set<Task> tasks;

        @ManyToOne
        @JoinColumn(name = "project_id")
        Project project;


    public Set<Task> getTasks() {
        return tasks;
    }
    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
