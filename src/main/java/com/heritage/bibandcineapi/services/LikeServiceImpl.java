package com.heritage.bibandcineapi.services;

import com.heritage.bibandcineapi.exception.ResourceNotFoundException;
import com.heritage.bibandcineapi.models.Like;
import com.heritage.bibandcineapi.models.LikeResponse;
import com.heritage.bibandcineapi.models.Post;
import com.heritage.bibandcineapi.models.User;
import com.heritage.bibandcineapi.repository.LikeRepository;
import com.heritage.bibandcineapi.repository.PostRepository;
import com.heritage.bibandcineapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements  LikeService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public LikeResponse addLike(Long userId, Long postId, Like likeRequest) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Post> post = postRepository.findById(postId);

        if (user.isEmpty()) {
        	throw new ResourceNotFoundException("User with id: " + userId + " not found");
        }
        if (post.isEmpty()) {
        	throw new ResourceNotFoundException("Post with id: " + postId + " not found");
        }
        likeRequest.setUser(user.get());
        likeRequest.setPost(post.get());
        likeRequest.setIsLiked(likeRequest.getIsLiked());

        Like newLiked = likeRepository.save(likeRequest);
        Long likeCounts = likeRepository.findAllLikes(postId);

        return new LikeResponse(newLiked, likeCounts);
    }

    @Override
    public Long deleteLike(Long userId, Long postId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Post> post = postRepository.findById(postId);

        if (user.isEmpty()) {
        	throw new ResourceNotFoundException("User with id: " + userId + " not found");
        }
        if (post.isEmpty()) {
        	throw new ResourceNotFoundException("Post with id: " + postId + " not found");
        }
        likeRepository.deleteByUserAndPost(user.get(), post.get());
        Long updatedLikes = likeRepository.findAllLikes(postId);
        return updatedLikes;
    }

    @Override
    public Long findAllLikesFromPost(Long postId) {
        return likeRepository.findAllLikes(postId);
    }

}
