package github.radek467.todoApp.model;

import java.util.List;
import java.util.Optional;

public interface TaskGroupRepository {
    List<TaskGroup> findAll();

    Optional<TaskGroup> findById (Integer id);

    boolean existsByDoneIsFalseAndProject_Id(Integer id);

    TaskGroup save (TaskGroup entity);
}
