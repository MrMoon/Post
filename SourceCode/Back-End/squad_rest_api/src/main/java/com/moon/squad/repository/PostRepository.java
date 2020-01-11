package com.moon.squad.repository;

import com.moon.squad.model.Post;
import com.moon.squad.repository.shared.CRUDRepository;
import com.moon.squad.shared.ApplicationConstants;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin (origins = ApplicationConstants.LOCALHOST_4200)
public interface PostRepository extends CRUDRepository<Post> {
    List<Post> findAllByOrderByDate();
}
