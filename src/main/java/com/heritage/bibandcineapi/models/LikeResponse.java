package com.heritage.bibandcineapi.models;

public class LikeResponse {

    private Like like;
    private Integer likeCounts;

    public LikeResponse(Like like, Integer likeCounts) {
        this.likeCounts = likeCounts;
        this.like = like;
    }
    public Integer getLikeCounts() {
        return likeCounts;
    }

    public Like getLike() {
        return like;
    }
}
