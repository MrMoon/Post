package com.moon.squad.service;

import com.moon.squad.model.Post;
import com.moon.squad.service.shared.CRUDService;

import java.util.List;

public interface PostService extends CRUDService<Post> {
    List<Post> findAllByOrderByDate();
}
