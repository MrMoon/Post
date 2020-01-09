package com.moon.squad.service.user.implementation;

import com.moon.squad.model.agile.Event;
import com.moon.squad.model.agile.Project;
import com.moon.squad.model.agile.Report;
import com.moon.squad.model.user.Team;
import com.moon.squad.model.user.User;
import com.moon.squad.repository.user.TeamRepository;
import com.moon.squad.service.user.TeamService;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import static com.moon.squad.shared.ApplicationConstants.CACHE_ID;
import static com.moon.squad.shared.ApplicationConstants.CACHE_TEAM;
import static com.moon.squad.shared.LoggingConstants.deleting;
import static com.moon.squad.shared.LoggingConstants.gettingAll;
import static com.moon.squad.shared.LoggingConstants.gettingById;
import static com.moon.squad.shared.LoggingConstants.saving;

@Service
@Slf4j
@CacheConfig (cacheNames = CACHE_TEAM)
public class TeamServiceImp implements TeamService {

    private final TeamRepository teamRepository;

    public TeamServiceImp(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Cacheable
    @Override
    public List<Team> findAllByTeamName(String teamName) {
        log.info(gettingAll(Team.class, false) + " by Name");
        return teamRepository.findAllByTeamName(teamName);
    }

    @Cacheable
    @Override
    public List<Team> findAllByOrderByTeamName() {
        log.info(gettingAll(Team.class, true) + " by Name");
        return teamRepository.findAllByOrderByTeamName();
    }

    @Cacheable
    @Override
    public List<User> findAllTeamMembersByTeamId(String id) {
        Optional<Team> team = teamRepository.findById(id);
        log.info("Getting Users in team with id " + id);
        return team.isPresent() ? team.get().getUsers() : new ArrayList<>();
    }

    @Cacheable
    @Override
    public List<Report> findAllTeamReportsByTeamId(String id) {
        log.info("Getting Reports in team with id " + id);
        Optional<Team> team = teamRepository.findById(id);
        return team.isPresent() ? team.get().getReports() : new ArrayList<>();
    }

    @Cacheable
    @Override
    public List<Event> findAllTeamEventsByTeamId(String id) {
        log.info("Getting Events in team with id " + id);
        Optional<Team> team = teamRepository.findById(id);
        return team.isPresent() ? team.get().getEvents() : new ArrayList<>();
    }

    @Cacheable
    @Override
    public List<Project> findAllTeamProjectsByTeamId(String id) {
        log.info("Getting Projects in team with id " + id);
        Optional<Team> team = teamRepository.findById(id);
        return team.isPresent() ? team.get().getProjects() : new ArrayList<>();
    }

    @Cacheable
    @Override
    public List<Team> findAll() {
        log.info(gettingAll(Team.class, false));
        return teamRepository.findAll();
    }

    @Cacheable (key = CACHE_ID)
    @Override
    public Optional<Team> findById(String id) {
        log.info(gettingById(Team.class, id));
        return teamRepository.findById(id);
    }

    @CachePut (value = "#team.id")
    @Override
    public Team saveOrUpdate(@NotNull Team team) {
        log.info(saving(team.toString()));
        return teamRepository.save(team);
    }

    @CacheEvict (key = CACHE_ID)
    @Override
    public void deleteUserById(String id) {
        log.info(deleting(Team.class, id));
        teamRepository.deleteById(id);
    }
}
