package com.moon.squad.service.user.implementation;

import com.moon.squad.model.user.Role;
import com.moon.squad.model.user.User;
import com.moon.squad.repository.user.RoleRepository;
import com.moon.squad.repository.user.UserRepository;
import com.moon.squad.service.user.UserService;
import com.moon.squad.shared.exception.RoleException;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import lombok.extern.slf4j.Slf4j;

import static com.moon.squad.shared.ApplicationConstants.CACHE_EMAIL;
import static com.moon.squad.shared.ApplicationConstants.CACHE_ID;
import static com.moon.squad.shared.ApplicationConstants.CACHE_USER;
import static com.moon.squad.shared.ApplicationConstants.ERROR_EMAIL_NOT_FOUND;
import static com.moon.squad.shared.ApplicationConstants.ROLE_NOT_FOUND;
import static com.moon.squad.shared.ApplicationConstants.USER;
import static com.moon.squad.shared.LoggingConstants.deleting;
import static com.moon.squad.shared.LoggingConstants.gettingAll;
import static com.moon.squad.shared.LoggingConstants.gettingById;
import static com.moon.squad.shared.LoggingConstants.idSearching;
import static com.moon.squad.shared.LoggingConstants.saving;

@Service
@Slf4j
@CacheConfig (cacheNames = CACHE_USER)
public class UserServiceImp implements UserService, UserDetailsService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public UserServiceImp() {
    }

    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Cacheable
    @Override
    public List<User> findAllByOrderByName() {
        log.info(gettingAll(User.class, true));
        return userRepository.findAllByOrderByName();
    }

    @Override
    public List<User> findAllFriends(String id) {
        Optional<User> user = userRepository.findById(id);
        return (user.isPresent()) ? user.get().getFriends() : new ArrayList<>();
    }

    @Cacheable (key = CACHE_EMAIL)
    @Override
    public Optional<User> findByEmail(@NotNull @NotBlank String email) {
        return userRepository.findByEmail(email);
    }

    @Cacheable
    @Override
    public List<User> findAll() {
        log.info(gettingAll(User.class, false));
        return userRepository.findAll();
    }

    @Cacheable (key = CACHE_ID)
    @Override
    public Optional<User> findById(String id) {
        @NotNull Optional<User> user = userRepository.findById(id);
        log.info(gettingById(User.class, id));
        if (user.isPresent()) {
            log.info(idSearching(User.class, id, true));
            return user;
        } else {
            log.info(idSearching(User.class, id, false));
            return Optional.empty();
        }
    }

    @CachePut (key = "#user.id")
    @Override
    public User saveOrUpdate(@NotNull User user) {
        log.info(saving(user.toString()));
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        user.setEnabled(true);
        Optional<Role> userRole = roleRepository.findByRole(USER);
        if (userRole.isPresent()) user.setRoles(new HashSet<>(Collections.singletonList(userRole.get())));
        else try {
            throw new RoleException(ROLE_NOT_FOUND);
        } catch (RoleException e) {
            e.printStackTrace();
        }
        userRepository.save(user);
        return null;
    }

    @CacheEvict (key = CACHE_ID)
    @Override
    public void deleteUserById(String id) {
        log.info(deleting(User.class, id));
        userRepository.deleteById(id);
    }

    @CachePut(key = CACHE_EMAIL)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            List<GrantedAuthority> authorities = getUserAuthority(user.get().getRoles());
            return buildUserForAuthentication(user.get(), authorities);
        }
        throw new UsernameNotFoundException(ERROR_EMAIL_NOT_FOUND);
    }

    @Override
    @Nullable
    @Contract (pure = true)
    public List<GrantedAuthority> getUserAuthority(@NotNull Set<Role> roles) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        roles.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole())));
        return new ArrayList<>(grantedAuthorities);
    }

    @Override
    public UserDetails buildUserForAuthentication(@NotNull User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
