package com.moon.squad.controller.user;

import com.moon.squad.model.user.Team;
import com.moon.squad.service.user.TeamService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.moon.squad.shared.Constants.*;

@RestController
@RequestMapping(TEAM_MAPPING)
@CrossOrigin(ALL_MAPPING)
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(SLASH)
    public List<Team> getAllTeams(){
        return teamService.findAllByOrderByTeamName();
    }

    @GetMapping(ID_MAPPING)
    public Optional<Team> getTeamById(@PathVariable(ID) String id){
        return teamService.findById(id);
    }

    @PostMapping(SLASH)
    public ResponseEntity<?> saveOrUpdateTeam(@RequestBody Team team){
        teamService.saveOrUpdate(team);
        return new ResponseEntity<>(team.toString() + "\n " + team.getClass().getSimpleName() + ' ' + ADDED_SUCCESSFULLY , HttpStatus.OK);
    }

    @DeleteMapping(ID_MAPPING)
    public ResponseEntity<?> deleteTeamById(@PathVariable String id){
        teamService.deleteById(id);
        return new ResponseEntity<>(id + "\n " + Team.class.getSimpleName() + ' ' + DELETED_SUCCESSFULLY , HttpStatus.OK);
    }
}
