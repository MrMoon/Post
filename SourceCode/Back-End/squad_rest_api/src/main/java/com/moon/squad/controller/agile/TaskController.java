package com.moon.squad.controller.agile;

import com.moon.squad.model.agile.Task;
import com.moon.squad.service.agile.TaskService;

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

import static com.moon.squad.shared.ApplicationConstants.ADDED_SUCCESSFULLY;
import static com.moon.squad.shared.ApplicationConstants.LOCALHOST_4200;
import static com.moon.squad.shared.ApplicationConstants.DELETED_SUCCESSFULLY;
import static com.moon.squad.shared.ApplicationConstants.ID;
import static com.moon.squad.shared.ApplicationConstants.ID_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.SLASH;
import static com.moon.squad.shared.ApplicationConstants.TASKS_MAPPING;

@RestController
@RequestMapping (TASKS_MAPPING)
@CrossOrigin (origins = LOCALHOST_4200)
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @GetMapping (SLASH)
    public List<Task> getAllTasks() {
        return taskService.findAllByOrderByDate();
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @GetMapping (ID_MAPPING)
    public Optional<Task> getTaskById(@PathVariable (ID) String id) {
        return taskService.findById(id);
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @PostMapping (SLASH)
    public ResponseEntity<?> saveOrUpdateTask(@RequestBody Task task) {
        taskService.saveOrUpdate(task);
        return new ResponseEntity<>(task.toString() + "\n " + task.getClass().getSimpleName() + ' ' + ADDED_SUCCESSFULLY, HttpStatus.OK);
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @DeleteMapping (ID_MAPPING)
    public ResponseEntity<?> deleteTaskById(@PathVariable String id) {
        taskService.deleteUserById(id);
        return new ResponseEntity<>(id + "\n " + Task.class.getSimpleName() + ' ' + DELETED_SUCCESSFULLY, HttpStatus.OK);
    }
}
