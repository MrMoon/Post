package com.moon.squad.service.user.implementations;

import com.moon.squad.model.user.User;
import com.moon.squad.repository.user.UserRepository;
import com.moon.squad.service.user.UserService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllByOrderById() {
        return userRepository.findAllByOrderById();
    }

    @Override
    public @NotNull Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveOrUpdate(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
