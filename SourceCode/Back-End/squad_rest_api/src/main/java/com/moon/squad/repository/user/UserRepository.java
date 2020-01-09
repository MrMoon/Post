package com.moon.squad.repository.user;

import com.moon.squad.model.user.User;
import com.moon.squad.repository.shared.CRUDRepository;

import org.hibernate.validator.constraints.UniqueElements;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface UserRepository extends CRUDRepository<User> {
    @NotNull List<User> findByName(@NotBlank @NotNull String name);

    @NotNull List<User> findAllByOrderByName();

    @NotNull List<User> findAllByJobTitle(@NotBlank @NotNull String jobTitle);

    Optional<User> findByEmail(@NotNull @NotBlank @Email String email);
}
