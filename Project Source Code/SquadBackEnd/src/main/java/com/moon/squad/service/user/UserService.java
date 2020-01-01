package com.moon.squad.service.user;

import com.moon.squad.model.user.User;
import com.moon.squad.service.shared.CRUDService;

import java.util.List;

public interface UserService extends CRUDService<User> {
    List<User> findAllByOrderById();
}
