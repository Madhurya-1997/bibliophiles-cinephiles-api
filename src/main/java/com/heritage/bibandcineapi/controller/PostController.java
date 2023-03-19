package com.heritage.bibandcineapi.controller;


import com.heritage.bibandcineapi.models.Post;
import com.heritage.bibandcineapi.repository.PostRepository;
import com.heritage.bibandcineapi.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;

@RestController
@CrossOrigin
@RequestMapping("api/posts")
public class PostController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Operation(summary = "Fetch all posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Posts fetched successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Resource not available",
                    content = @Content)
    })
    @GetMapping("")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }


    @Operation(summary = "Fetch all posts by a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Posts fetched from user successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Resource not available",
                    content = @Content)
    })
    @GetMapping("/{userId}")
    public Page<Post> getAllPostsByUserId(@PathVariable(value = "userId")Long userId
            , Pageable pageable) {
        return postRepository.findByUserId(userId, pageable);
    }

    @Operation(summary = "Add new post by a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User successfully created a new post",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized personnel",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Resource not available",
                    content = @Content)
    })
    @PostMapping("/{userId}")
    public Post createPost(@PathVariable(value="userId") Long userId, @RequestBody Post post) {
        return userRepository.findById(userId).map(user -> {
             post.setUser(user);
             return postRepository.save(post);
        }).orElseThrow(() -> new ResolutionException("User with ID: " + userId + " not found !!" ));
    }


    @Operation(summary = "Modify existing post by a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User successfully modified their post",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized personnel",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Resource not available",
                    content = @Content)
    })
    @PutMapping("/{userId}/{postId}")
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
