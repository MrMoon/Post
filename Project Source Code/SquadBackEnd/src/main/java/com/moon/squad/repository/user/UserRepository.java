package com.moon.squad.repository.user;

import com.moon.squad.model.user.User;
import com.moon.squad.repository.shared.CRUDRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface UserRepository extends CRUDRepository<User> {
    @NotNull List<User> findByName(String name);
    @NotNull List<User> findAllByOrderById();
    @NotNull List<User> findAllByJobTitle(String jobTitle);
}
