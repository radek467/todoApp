package github.radek467.todoApp.controller;

import github.radek467.todoApp.model.Project;
import github.radek467.todoApp.model.ProjectRepository;
import github.radek467.todoApp.model.ProjectStep;
import github.radek467.todoApp.model.Task;
import github.radek467.todoApp.model.projection.ProjectWriteModel;
import github.radek467.todoApp.service.ProjectService;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import javax.naming.Binding;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/projects")
class ProjectController {

    private final ProjectService projectService;
    private final ProjectRepository projectRepository;

    ProjectController(ProjectService projectService, ProjectRepository projectRepository)
    {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
    }
    @GetMapping
    String showProjects(Model model)
    {
        model.addAttribute("project", new ProjectWriteModel());
        return "projects";
    }

    @PostMapping(params = "addStep")
    String addProjectStep(@ModelAttribute("project") ProjectWriteModel current)
    {
        current.getSteps().add(new ProjectStep());
        return "projects";
    }

    @PostMapping
    String addProject(@ModelAttribute("project") @Valid ProjectWriteModel toAdd,
                      BindingResult bindingResult,
                      Model model)
    {
        if(bindingResult.hasErrors()){
            return "projects";
        }
        projectService.save(toAdd);
        System.out.println("dodano");
        model.addAttribute("project", new ProjectWriteModel());
        model.addAttribute("projects", getProjects());
        model.addAttribute("message", "Dodano projekt");
        return "projects";
    }

    @PostMapping("/{id}")
    String createGroup(
            @PathVariable int id,
            Model model,
            ProjectWriteModel projectWriteModel,
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")LocalDateTime deadline
            )
    {
        System.out.println(id + "xd" + deadline.toString());
        try{
            projectService.createGroup(deadline, id);
            model.addAttribute("message", "created successfully");
        }catch (IllegalArgumentException | IllegalStateException | NullPointerException ex){
            model.addAttribute("message", "something wrong" + ex.getMessage());
        }
        return "projects";
    }


    @ModelAttribute("projects")
    public List<Project> getProjects()
    {
        return projectRepository.findAll();
    }
}
