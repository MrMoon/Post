package com.moon.squad.repository.user;

import com.moon.squad.model.user.Team;
import com.moon.squad.repository.shared.CRUDRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.validation.constraints.NotBlank;

public interface TeamRepository extends CRUDRepository<Team> {
    @NotNull List<Team> findAllByTeamName(@NotNull @NotBlank String teamName);

    @NotNull List<Team> findAllByOrderByTeamName();
}
