package github.radek467.todoApp.model;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    Optional<Project> findById (Integer id);

    List<Project> findAll();

    Project save (Project entity);
}
