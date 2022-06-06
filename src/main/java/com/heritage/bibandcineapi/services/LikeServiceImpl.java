package com.heritage.bibandcineapi.services;

import com.heritage.bibandcineapi.models.Like;
import com.heritage.bibandcineapi.models.LikeResponse;
import com.heritage.bibandcineapi.models.Post;
import com.heritage.bibandcineapi.models.User;
import com.heritage.bibandcineapi.repository.LikeRepository;
import com.heritage.bibandcineapi.repository.PostRepository;
import com.heritage.bibandcineapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public LikeResponse addLike(Long postId, Long userId, Like likeRequest) {
        return setLikeDetails(postId, userId, likeRequest);
    }

    @Override
    public LikeResponse deleteLike(Long postId, Long userId, Like likeRequest) {
        return setLikeDetails(postId, userId, likeRequest);
    }

    private LikeResponse setLikeDetails(Long postId, Long userId, Like likeRequest) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Post> post = postRepository.findById(postId);

        likeRequest.setUser(user.get());
        likeRequest.setPost(post.get());
        likeRequest.setIsLiked(likeRequest.getIsLiked());

        Like newLiked = likeRepository.save(likeRequest);

        Integer likeCounts = likeRepository.findAllLikes(postId, likeRequest.getIsLiked());

        return new LikeResponse(newLiked, likeCounts);
    }
}
