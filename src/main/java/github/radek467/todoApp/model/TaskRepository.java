package github.radek467.todoApp.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();
    Optional<Task> findById(Integer id);
    Task save(Task task);
    List<Task> findByDone(boolean done);
    List<Task> findAllByGroup_Id(Integer groupId);
    Page<Task> findAll(Pageable page);
    boolean existsById(Integer id);
    boolean existsByDoneIsFalseAndGroup_Id(Integer groupId);
    List<Task> getTasksByDoneIsFalseAndDeadlineIsNullOrDeadlineIsBefore(LocalDateTime dateTime);
}
