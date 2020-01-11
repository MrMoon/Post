package com.moon.squad.repository.user;

import com.moon.squad.model.user.User;
import com.moon.squad.repository.shared.CRUDRepository;
import com.moon.squad.shared.ApplicationConstants;

import org.hibernate.validator.constraints.UniqueElements;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@CrossOrigin (origins = ApplicationConstants.LOCALHOST_4200)
public interface UserRepository extends CRUDRepository<User> {
    @NotNull List<User> findByName(@NotBlank @NotNull String name);

    @NotNull List<User> findAllByOrderByName();

    @NotNull List<User> findAllByJobTitle(@NotBlank @NotNull String jobTitle);

    Optional<User> findByEmail(@NotNull @NotBlank @Email String email);
}
