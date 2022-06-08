package com.heritage.bibandcineapi.services;

import com.heritage.bibandcineapi.models.Like;
import com.heritage.bibandcineapi.models.LikeResponse;

public interface LikeService {

    public LikeResponse addLike(Long userId, Long postId, Like like);
    public LikeResponse deleteLike(Long userId, Long postId, Like like);
    public Long findAllLikesFromPost(Long postId);
}
