package com.moon.squad.service.agile.implementations;

import com.moon.squad.model.agile.Project;
import com.moon.squad.repository.agile.ProjectRepository;
import com.moon.squad.repository.shared.SequenceRepository;
import com.moon.squad.service.agile.ProjectService;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImp implements ProjectService {

    private final ProjectRepository projectRepository;
    private final SequenceRepository sequenceRepository;

    public ProjectServiceImp(ProjectRepository projectRepository, SequenceRepository sequenceRepository) {
        this.projectRepository = projectRepository;
        this.sequenceRepository = sequenceRepository;
    }

    @Override
    public List<Project> findAllOrderByDeadline() {
        return projectRepository.findAllByOrderByDeadline();
    }

    @Override
    public List<Project> findAllByDeadline(Date date) {
        return projectRepository.findAllByDeadline(date);
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> findById(String id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project saveOrUpdate(@NotNull Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteById(String id) {
        projectRepository.deleteById(id);
    }
}
