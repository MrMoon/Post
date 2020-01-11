package com.moon.squad.service.user;

import com.moon.squad.model.Post;
import com.moon.squad.repository.PostRepository;
import com.moon.squad.service.PostService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import static com.moon.squad.shared.ApplicationConstants.CACHE_ID;
import static com.moon.squad.shared.ApplicationConstants.CACHE_POST;
import static com.moon.squad.shared.LoggingConstants.deleting;
import static com.moon.squad.shared.LoggingConstants.gettingAll;
import static com.moon.squad.shared.LoggingConstants.gettingById;
import static com.moon.squad.shared.LoggingConstants.saving;

@Service
@Slf4j
@CacheConfig (cacheNames = CACHE_POST)
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Cacheable
    @Override
    public List<Post> findAllByOrderByDate() {
        log.info(gettingAll(Post.class, true));
        return postRepository.findAllByOrderByDate();
    }

    @Cacheable
    @Override
    public List<Post> findAll() {
        log.info(gettingAll(Post.class, false));
        return postRepository.findAll();
    }

    @Cacheable (key = CACHE_ID)
    @Override
    public Optional<Post> findById(String id) {
        log.info(gettingById(Post.class, id));
        return postRepository.findById(id);
    }

    @CachePut (key = "#post.getId()")
    @Override
    public Post saveOrUpdate(@NotNull Post post) {
        log.info(saving(post.toString()));
        return postRepository.save(post);
    }

    @CacheEvict (key = CACHE_ID)
    @Override
    public void deleteUserById(String id) {
        log.info(deleting(Post.class, id));
        postRepository.deleteById(id);
    }
}
