package com.moon.squad.repository.user;

import com.moon.squad.model.user.Team;
import com.moon.squad.repository.shared.CRUDRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends CRUDRepository<Team> {
    Optional<Team> findByTeamName(String teamname);
    List<Team> findAllByOrderByTeamName();
}
