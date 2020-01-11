package com.moon.squad.service.user;

import com.moon.squad.model.user.Role;
import com.moon.squad.model.user.User;
import com.moon.squad.service.shared.CRUDService;
import com.moon.squad.shared.ApplicationConstants;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.NotBlank;

@CrossOrigin (origins = ApplicationConstants.LOCALHOST_4200)
public interface UserService extends CRUDService<User> {
    List<User> findAllByOrderByName();

    List<User> findAllFriends(String id);

    Optional<User> findByEmail(@NotNull @NotBlank String email);

    List<GrantedAuthority> getUserAuthority(Set<Role> roles);

    void addFriend(String userId , String friendId);

    String findUserIdByEmail(String email);

    UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities);
}
