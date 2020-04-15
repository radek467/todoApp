package github.radek467.todoApp.model;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank (message = "Description mustn't be blank")
    private String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    Set<ProjectStep> steps;

    @OneToMany(mappedBy = "project")
    Set<TaskGroup> taskGroups;

    public Integer getId() { return id; }

    void setId(Integer id) { this.id = id; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    Set<ProjectStep> getSteps() { return steps; }

    void setSteps(Set<ProjectStep> steps) { this.steps = steps; }

    Set<TaskGroup> getTaskGroups() { return taskGroups; }

    void setTaskGroups(Set<TaskGroup> taskGroups) { this.taskGroups = taskGroups; }
}
