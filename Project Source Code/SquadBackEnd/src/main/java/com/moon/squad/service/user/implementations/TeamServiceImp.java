package com.moon.squad.service.user.implementations;

import com.moon.squad.model.user.Team;
import com.moon.squad.repository.shared.SequenceRepository;
import com.moon.squad.repository.user.TeamRepository;
import com.moon.squad.service.user.TeamService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImp implements TeamService {

    private final TeamRepository teamRepository;
    private final SequenceRepository sequenceRepository;

    @Autowired
    public TeamServiceImp(TeamRepository teamRepository, SequenceRepository sequenceRepository) {
        this.teamRepository = teamRepository;
        this.sequenceRepository = sequenceRepository;
    }

    @Override
    public Optional<Team> findByTeamName(String teamName) {
        return teamRepository.findByTeamName(teamName);
    }

    @Override
    public List<Team> findAllByOrderByTeamName() {
        return teamRepository.findAllByOrderByTeamName();
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> findById(String id) {
        return teamRepository.findById(id);
    }

    @Override
    public Team saveOrUpdate(@NotNull Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void deleteById(String id) {
        teamRepository.deleteById(id);
    }
}
