package com.moon.squad.controller.user;

import com.moon.squad.model.agile.Event;
import com.moon.squad.model.agile.Project;
import com.moon.squad.model.agile.Report;
import com.moon.squad.model.user.Team;
import com.moon.squad.model.user.User;
import com.moon.squad.service.user.TeamService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import static com.moon.squad.shared.ApplicationConstants.DELETED_SUCCESSFULLY;
import static com.moon.squad.shared.ApplicationConstants.EVENT_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.ID;
import static com.moon.squad.shared.ApplicationConstants.ID_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.NAME;
import static com.moon.squad.shared.ApplicationConstants.NAME_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.PROJECT_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.REPORT_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.SLASH;
import static com.moon.squad.shared.ApplicationConstants.TEAM_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.USER_MAPPING;

@RestController
@RequestMapping (TEAM_MAPPING)
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping (SLASH)
    public List<Team> getAllTeams() {
        return teamService.findAllByOrderByTeamName();
    }

    @GetMapping (ID_MAPPING)
    public Optional<Team> getTeamById(@PathVariable (ID) String id) {
        return teamService.findById(id);
    }

    @GetMapping (NAME_MAPPING)
    public List<Team> getTeamByName(@PathVariable (NAME) String name) {
        return teamService.findAllByTeamName(name);
    }

    @GetMapping (ID_MAPPING + USER_MAPPING)
    public List<User> getAllTeamMembers(@PathVariable (ID) String id) {
        return teamService.findAllTeamMembersByTeamId(id);
    }

    @GetMapping (ID_MAPPING + REPORT_MAPPING)
    public List<Report> getAllTeamReports(@PathVariable (ID) String id) {
        return teamService.findAllTeamReportsByTeamId(id);
    }

    @GetMapping (ID_MAPPING + EVENT_MAPPING)
    public List<Event> getAllTeamEvents(@PathVariable (ID) String id) {
        return teamService.findAllTeamEventsByTeamId(id);
    }

    @GetMapping (ID_MAPPING + PROJECT_MAPPING)
    public List<Project> getAllTeamProjects(@PathVariable (ID) String id) {
        return teamService.findAllTeamProjectsByTeamId(id);
    }

    @PostMapping (SLASH)
    public ResponseEntity<?> saveOrUpdateTeam(@RequestBody Team team) {
        teamService.saveOrUpdate(team);
        return new ResponseEntity<>(team.toString() + "\n " + team.getClass().getSimpleName() + ' ' + ADDED_SUCCESSFULLY, HttpStatus.OK);
    }

    @DeleteMapping (ID_MAPPING)
    public ResponseEntity<?> deleteTeamById(@PathVariable String id) {
        teamService.deleteUserById(id);
        return new ResponseEntity<>(id + "\n " + Team.class.getSimpleName() + ' ' + DELETED_SUCCESSFULLY, HttpStatus.OK);
    }
}
