package com.moon.squad.service.user;

import com.moon.squad.model.user.Role;
import com.moon.squad.model.user.User;
import com.moon.squad.service.shared.CRUDService;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.NotBlank;

public interface UserService extends CRUDService<User> {
    List<User> findAllByOrderByName();

    Optional<User> findByEmail(@NotNull @NotBlank String email);

    List<GrantedAuthority> getUserAuthority(Set<Role> roles);

    UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities);
}
