package com.moon.squad.repository.user;

import com.moon.squad.model.user.Role;
import com.moon.squad.repository.shared.CRUDRepository;
import com.moon.squad.shared.ApplicationConstants;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

@CrossOrigin (origins = ApplicationConstants.LOCALHOST_4200)
public interface RoleRepository extends CRUDRepository<Role> {
    Optional<Role> findByRole(@NotNull @NotBlank String role);
}
