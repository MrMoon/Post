package com.moon.squad.controller.agile;

import com.moon.squad.model.agile.Project;
import com.moon.squad.service.agile.ProjectService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.moon.squad.shared.Constants.ADDED_SUCCESSFULLY;
import static com.moon.squad.shared.Constants.ALL_MAPPING;
import static com.moon.squad.shared.Constants.DELETED_SUCCESSFULLY;
import static com.moon.squad.shared.Constants.ID;
import static com.moon.squad.shared.Constants.ID_MAPPING;
import static com.moon.squad.shared.Constants.PROJECT_MAPPING;
import static com.moon.squad.shared.Constants.SLASH;

@RestController
@RequestMapping(PROJECT_MAPPING)
@CrossOrigin(ALL_MAPPING)
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(SLASH)
    public List<Project> getAllProjects(){
        return projectService.findAll();
    }

    @GetMapping(ID_MAPPING)
    public Optional<Project> getProjectById(@PathVariable(ID) String id){
        return projectService.findById(id);
    }

    @PostMapping(SLASH)
    public ResponseEntity<?> saveOrUpdateProject(@RequestBody Project project){
        projectService.saveOrUpdate(project);
        return new ResponseEntity<>(project.toString() + "\n " + project.getClass().getSimpleName() + ' ' + ADDED_SUCCESSFULLY , HttpStatus.OK);
    }

    @DeleteMapping(ID_MAPPING)
    public ResponseEntity<?> deleteProjectById(@PathVariable String id){
        projectService.deleteById(id);
        return new ResponseEntity<>(id + "\n " + Project.class.getSimpleName() + ' ' + DELETED_SUCCESSFULLY , HttpStatus.OK);
    }
}
