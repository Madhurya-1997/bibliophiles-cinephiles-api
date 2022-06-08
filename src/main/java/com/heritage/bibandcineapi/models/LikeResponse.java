package com.heritage.bibandcineapi.models;

public class LikeResponse {

    private Like like;
    private Long likeCounts;

    public LikeResponse(Like like, Long likeCounts) {
        this.likeCounts = likeCounts;
        this.like = like;
    }
    public Long getLikeCounts() {
        return likeCounts;
    }

    public Like getLike() {
        return like;
    }
}
