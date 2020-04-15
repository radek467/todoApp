package github.radek467.todoApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
    @Table (name = "task_groups")
    public class TaskGroup extends BaseTaskDataEntity {
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private int id;
//        @NotBlank
//        private String description;
//        private boolean done;
        @Embedded
        private Audit audit = new Audit();

        @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "group")
        private Set<Task> tasks;

        @ManyToOne
        @JoinColumn(name = "project_id")
        Project project;


//        public int getId() {
//            return id;
//        }
//
//        void setId(int id) {
//            this.id = id;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        void setDescription(String description) {
//            this.description = description;
//        }
//
//        public boolean isDone() {
//            return done;
//        }
//
//
//        public void setDone(boolean done) {
//            this.done = done;
//        }

}
