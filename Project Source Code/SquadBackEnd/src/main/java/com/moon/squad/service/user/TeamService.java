package com.moon.squad.service.user;

import com.moon.squad.model.user.Team;
import com.moon.squad.service.shared.CRUDService;

import java.util.List;
import java.util.Optional;

public interface TeamService extends CRUDService<Team> {
    Optional<Team> findByTeamName(String teamName);
    List<Team> findAllByOrderByTeamName();
}
