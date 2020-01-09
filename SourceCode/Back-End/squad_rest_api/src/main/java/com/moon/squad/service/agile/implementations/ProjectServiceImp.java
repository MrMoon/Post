package com.moon.squad.service.agile.implementations;

import com.moon.squad.model.agile.Project;
import com.moon.squad.model.agile.Task;
import com.moon.squad.model.user.User;
import com.moon.squad.repository.agile.ProjectRepository;
import com.moon.squad.repository.shared.SequenceRepository;
import com.moon.squad.service.agile.ProjectService;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import static com.moon.squad.shared.ApplicationConstants.CACHE_ID;
import static com.moon.squad.shared.ApplicationConstants.CACHE_PROJECT;
import static com.moon.squad.shared.LoggingConstants.deleting;
import static com.moon.squad.shared.LoggingConstants.gettingAll;
import static com.moon.squad.shared.LoggingConstants.gettingById;
import static com.moon.squad.shared.LoggingConstants.saving;

@Service
@CacheConfig (cacheNames = CACHE_PROJECT)
@Slf4j
public class ProjectServiceImp implements ProjectService {

    private final ProjectRepository projectRepository;
    private final SequenceRepository sequenceRepository;

    public ProjectServiceImp(ProjectRepository projectRepository, SequenceRepository sequenceRepository) {
        this.projectRepository = projectRepository;
        this.sequenceRepository = sequenceRepository;
    }

    @Cacheable
    @Override
    public List<Project> findAllOrderByDeadline() {
        log.info(gettingAll(Project.class, true) + " By Deadline");
        return projectRepository.findAllByOrderByDeadline();
    }

    @Cacheable
    @Override
    public List<Project> findAllByDeadline(@NotNull Date date) {
        log.info("Getting All Projects By Deadline " + date.toString());
        return projectRepository.findAllByDeadline(date);
    }

    @Cacheable
    @Override
    public List<User> findAllProjectUsersByProjectId(String id) {
        log.info("Getting All Users from project with id " + id);
        Optional<Project> project = projectRepository.findById(id);
        return project.isPresent() ? project.get().getUsers() : new ArrayList<>();
    }

    @Cacheable
    @Override
    public List<Task> findAllProjectTasksByProjectId(String id) {
        log.info("Getting All Tasks from project with id " + id);
        Optional<Project> project = projectRepository.findById(id);
        return project.isPresent() ? project.get().getTasks() : new ArrayList<>();
    }

    @Cacheable
    @Override
    public List<Project> findAll() {
        log.info(gettingAll(Project.class, false));
        return projectRepository.findAll();
    }

    @Cacheable (key = CACHE_ID)
    @Override
    public Optional<Project> findById(String id) {
        log.info(gettingById(Project.class, id));
        return projectRepository.findById(id);
    }

    @CachePut (key = "#project.id")
    @Override
    public Project saveOrUpdate(@NotNull Project project) {
        log.info(saving(project.toString()));
        return projectRepository.save(project);
    }

    @CacheEvict (key = CACHE_ID)
    @Override
    public void deleteUserById(String id) {
        log.info(deleting(Project.class, id));
        projectRepository.deleteById(id);
    }
}
