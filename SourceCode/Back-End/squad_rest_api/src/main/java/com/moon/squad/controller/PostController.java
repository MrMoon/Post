package com.moon.squad.controller;

import com.moon.squad.model.Post;
import com.moon.squad.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.moon.squad.shared.ApplicationConstants.ADDED_SUCCESSFULLY;
import static com.moon.squad.shared.ApplicationConstants.DELETED_SUCCESSFULLY;
import static com.moon.squad.shared.ApplicationConstants.ID_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.LOCALHOST_4200;
import static com.moon.squad.shared.ApplicationConstants.POST_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.SLASH;

@RestController
@CrossOrigin (origins = LOCALHOST_4200)
@RequestMapping (POST_MAPPING)
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping (SLASH)
    public List<Post> getAllByOrderByDate() {
        return postService.findAllByOrderByDate();
    }

    @PostMapping (SLASH)
    public ResponseEntity<?> saveOrUpdatePost(@RequestBody Post post) {
        postService.saveOrUpdate(post);
        return ResponseEntity.ok(post.getClass().getSimpleName() + ' ' + ADDED_SUCCESSFULLY);
    }

    @DeleteMapping (ID_MAPPING)
    public ResponseEntity<?> deletePost(@PathVariable String id) {
        postService.deleteUserById(id);
        return ResponseEntity.ok(Post.class.getSimpleName() + ' ' + DELETED_SUCCESSFULLY);
    }
}
