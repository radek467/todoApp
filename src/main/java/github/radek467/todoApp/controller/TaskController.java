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
import java.util.List;
import java.util.Optional;

@RestController
class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository taskRepository;

    public TaskController(final TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @GetMapping(value = "/tasks", params = {"!size", "!page", "!sort"})
    ResponseEntity<List<Task>> readAllTasks(){
        logger.info("Exposing all tasks");
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @GetMapping(value = "/tasks")
    ResponseEntity <List<Task>> readAllTasks(Pageable page){
        logger.info("Custom pageable");
        return ResponseEntity.ok(taskRepository.findAll(page).getContent());
    }

    @GetMapping(value = "/tasks/{id}")
    ResponseEntity<?> getTaskById(@PathVariable int id)
    {
        Optional task = taskRepository.findById(id);
        if(task.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping(value = "/tasks")
    ResponseEntity<?> createTask(@RequestBody @Valid Task task)
    {
        taskRepository.save(task);
        return ResponseEntity.created(URI.create("/" + task.getId())).body(task);
    }

    @Transactional
    @PatchMapping(value = "tasks/{id}")
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
    @PutMapping(value = "/tasks/{id}")
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
