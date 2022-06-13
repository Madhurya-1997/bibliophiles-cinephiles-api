package com.heritage.bibandcineapi.services;

import com.heritage.bibandcineapi.models.Feedback;
import com.heritage.bibandcineapi.models.FeedbackRequest;

import java.util.List;

public interface FeedbackService {

    public Feedback addFeedback(Long userId, Long postId, FeedbackRequest feedbackRequest);
    public List<Feedback> findAllFeedbacksFromPost(Long postId);
}
