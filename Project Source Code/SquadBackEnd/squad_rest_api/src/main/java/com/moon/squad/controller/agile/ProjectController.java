package com.moon.squad.controller.agile;

import com.moon.squad.model.agile.Project;
import com.moon.squad.model.agile.Task;
import com.moon.squad.model.user.User;
import com.moon.squad.service.agile.ProjectService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.moon.squad.shared.ApplicationConstants.ADDED_SUCCESSFULLY;
import static com.moon.squad.shared.ApplicationConstants.DATE;
import static com.moon.squad.shared.ApplicationConstants.DELETED_SUCCESSFULLY;
import static com.moon.squad.shared.ApplicationConstants.ID;
import static com.moon.squad.shared.ApplicationConstants.ID_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.ORDER_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.PROJECT_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.SLASH;
import static com.moon.squad.shared.ApplicationConstants.TASKS_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.USER_MAPPING;

@RestController
@RequestMapping (PROJECT_MAPPING)
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping (SLASH)
    public List<Project> getAllProjects() {
        return projectService.findAll();
    }

    @GetMapping (ORDER_MAPPING)
    public List<Project> getAllProjectsOrderByDeadline() {
        return projectService.findAllOrderByDeadline();
    }

    @GetMapping (DATE)
    public List<Project> getProjectByDeadline(@PathVariable (DATE) Date date) {
        return projectService.findAllByDeadline(date);
    }

    @GetMapping (ID_MAPPING)
    public Optional<Project> getProjectById(@PathVariable (ID) String id) {
        return projectService.findById(id);
    }

    @GetMapping (ID_MAPPING + USER_MAPPING)
    public List<User> getAllProjectUsers(@PathVariable (ID) String id) {
        return projectService.findAllProjectUsersByProjectId(id);
    }

    @GetMapping (ID_MAPPING + TASKS_MAPPING)
    public List<Task> getAllProjectTasks(@PathVariable (ID) String id) {
        return projectService.findAllProjectTasksByProjectId(id);
    }

    @PostMapping (SLASH)
    public ResponseEntity<?> saveOrUpdateProject(@RequestBody Project project) {
        projectService.saveOrUpdate(project);
        return new ResponseEntity<>(project.toString() + "\n " + project.getClass().getSimpleName() + ' ' + ADDED_SUCCESSFULLY, HttpStatus.OK);
    }

    @DeleteMapping (ID_MAPPING)
    public ResponseEntity<?> deleteProjectById(@PathVariable String id) {
        projectService.deleteUserById(id);
        return new ResponseEntity<>(id + "\n " + Project.class.getSimpleName() + ' ' + DELETED_SUCCESSFULLY, HttpStatus.OK);
    }
}
