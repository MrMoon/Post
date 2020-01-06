package com.moon.squad.service.agile.implementations;

import com.moon.squad.model.agile.Task;
import com.moon.squad.repository.agile.TaskRepository;
import com.moon.squad.repository.shared.SequenceRepository;
import com.moon.squad.service.agile.TaskService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import static com.moon.squad.shared.ApplicationConstants.CACHE_ID;
import static com.moon.squad.shared.ApplicationConstants.CACHE_TASK;
import static com.moon.squad.shared.LoggingConstants.deleting;
import static com.moon.squad.shared.LoggingConstants.gettingAll;
import static com.moon.squad.shared.LoggingConstants.gettingById;
import static com.moon.squad.shared.LoggingConstants.saving;

@Service
@CacheConfig (cacheNames = CACHE_TASK)
@Slf4j
public class TaskServiceImp implements TaskService {

    private final SequenceRepository sequenceRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImp(TaskRepository taskRepository, SequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
        this.taskRepository = taskRepository;
    }

    @Cacheable
    @Override
    public List<Task> findAll() {
        log.info(gettingAll(Task.class, false));
        return taskRepository.findAll();
    }

    @Cacheable
    @Override
    public List<Task> findAllByOrderByDate() {
        log.info(gettingAll(Task.class, true) + " By date");
        return taskRepository.findAllByOrderByDate();
    }

    @Cacheable
    @Override
    public List<Task> findAllByDate(Date date) {
        log.info("Getting All Tasks By Date " + date.toString());
        return taskRepository.findAllByDate(date);
    }

    @Cacheable (key = CACHE_ID)
    @Override
    public @NotNull Optional<Task> findById(String id) {
        log.info(gettingById(Task.class, id));
        return taskRepository.findById(id);
    }

    @CachePut (key = "#task.id")
    @Override
    public Task saveOrUpdate(@NotNull Task task) {
        log.info(saving(task.toString()));
        return taskRepository.save(task);
    }

    @CacheEvict (key = CACHE_ID)
    @Override
    public void deleteUserById(String id) {
        log.info(deleting(Task.class, id));
        taskRepository.deleteById(id);
    }
}
