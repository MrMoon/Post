package com.moon.squad.repository.user;

import com.moon.squad.model.user.Team;
import com.moon.squad.repository.shared.CRUDRepository;
import com.moon.squad.shared.ApplicationConstants;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import javax.validation.constraints.NotBlank;

@CrossOrigin (origins = ApplicationConstants.LOCALHOST_4200)
public interface TeamRepository extends CRUDRepository<Team> {
    @NotNull List<Team> findAllByTeamName(@NotNull @NotBlank String teamName);

    @NotNull List<Team> findAllByOrderByTeamName();
}
