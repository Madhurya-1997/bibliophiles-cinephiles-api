package com.heritage.bibandcineapi.controller;


import com.heritage.bibandcineapi.models.Post;
import com.heritage.bibandcineapi.repository.PostRepository;
import com.heritage.bibandcineapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;

@RestController
@RequestMapping("api/users")
public class PostController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/{userId}/posts")
    public Page<Post> getAllPostsByUserId(@PathVariable(value = "userId")Long userId
            , Pageable pageable) {
        return postRepository.findByUserId(userId, pageable);
    }
    @PostMapping("/{userId}/posts")
    public Post createPost(@PathVariable(value="userId") Long userId, @RequestBody Post post) {
        return userRepository.findById(userId).map(user -> {
             post.setUser(user);
             return postRepository.save(post);
        }).orElseThrow(() -> new ResolutionException("User with ID: " + userId + " not found !!" ));
    }
    @PutMapping("/{userId}/posts/{postId}")
    public Post updatePost(@PathVariable(value="userId") Long userId,
                           @PathVariable(value="postId") Long postId,
                           @RequestBody Post postRequest) {
        return userRepository.findById(userId).map(user -> postRepository.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setDescription(postRequest.getDescription());
            return postRepository.save(post);
        }).orElseThrow(() -> new ResolutionException("No post found with ID: " + postId)))
                .orElseThrow(() -> new ResolutionException("No user found with ID: " + userId));
    }
}
