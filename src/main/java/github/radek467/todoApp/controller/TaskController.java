package github.radek467.todoApp.controller;

import github.radek467.todoApp.model.Task;
import github.radek467.todoApp.model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RequestMapping("/tasks")
@RestController
class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository taskRepository;

    public TaskController(final TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @GetMapping(value = "", params = {"!size", "!page", "!sort"})
    ResponseEntity<List<Task>> readAllTasks(){
        logger.info("Exposing all tasks");
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @GetMapping(value = "")
    ResponseEntity <List<Task>> readAllTasks(Pageable page){
        logger.info("Custom pageable");
        return ResponseEntity.ok(taskRepository.findAll(page).getContent());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<?> getTaskById(@PathVariable int id)
    {
        Optional task = taskRepository.findById(id);
        if(task.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @GetMapping(value = "/today")
    ResponseEntity<List<Task>> getTasksOnToday()
    {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
        //List<Task> result = taskRepository.getTasksByDeadlineIsNullOrDeadlineBefore(localDateTime);
        List<Task> result = taskRepository.getTasksByDoneIsFalseAndDeadlineIsNullOrDeadlineIsBefore(localDateTime);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "")
    ResponseEntity<?> createTask(@RequestBody @Valid Task task)
    {
        taskRepository.save(task);
        return ResponseEntity.created(URI.create("/" + task.getId())).body(task);
    }

    @Transactional
    @PatchMapping(value = "/{id}")
        public ResponseEntity<?> toggleTask (@PathVariable int id)
    {
        if(!taskRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        taskRepository.findById(id)
                .ifPresent(task -> task.setDone(!task.isDone()));
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateTask(@RequestBody @Valid Task task, @PathVariable int id){
        if(!taskRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        taskRepository.findById(id)
                .ifPresent(t -> {
                    t.updateFrom(task);
                    taskRepository.save(t);
                });
        return ResponseEntity.noContent().build();
    }




}
