package com.moon.squad.service.agile.implementations;

import com.moon.squad.model.agile.Task;
import com.moon.squad.repository.agile.TaskRepository;
import com.moon.squad.repository.shared.SequenceRepository;
import com.moon.squad.service.agile.TaskService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImp implements TaskService {

    private final SequenceRepository sequenceRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImp(TaskRepository taskRepository , SequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findAllByOrderByDate() {
        return taskRepository.findAllByOrderByDate();
    }

    @Override
    public List<Task> findAllByDate(Date date) {
        return taskRepository.findAllByDate(date);
    }

    @Override
    public @NotNull Optional<Task> findById(String id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task saveOrUpdate(@NotNull Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteById(String id) {
        taskRepository.deleteById(id);
    }
}
