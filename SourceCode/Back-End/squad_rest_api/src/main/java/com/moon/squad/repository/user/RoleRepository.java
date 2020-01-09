package com.moon.squad.repository.user;

import com.moon.squad.model.user.Role;
import com.moon.squad.repository.shared.CRUDRepository;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

public
interface RoleRepository extends CRUDRepository<Role> {
    Optional<Role> findByRole(@NotNull @NotBlank String role);
}
