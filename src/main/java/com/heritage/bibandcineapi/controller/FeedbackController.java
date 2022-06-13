package com.heritage.bibandcineapi.controller;

import com.heritage.bibandcineapi.models.Feedback;
import com.heritage.bibandcineapi.models.FeedbackRequest;
import com.heritage.bibandcineapi.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;


    @GetMapping("/{postId}")
    public ResponseEntity<List<Feedback>> getAllFeedbacksFromPost(@PathVariable("postId")Long postId) {
        return new ResponseEntity<List<Feedback>>(feedbackService.findAllFeedbacksFromPost(postId), HttpStatus.OK);
    }

    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<Feedback> postFeedback(@PathVariable("userId") Long userId,
                                          @PathVariable("postId")Long postId,
                                          @RequestBody FeedbackRequest feedbackRequest) {
        return new ResponseEntity<Feedback>(feedbackService.addFeedback(userId, postId, feedbackRequest), HttpStatus.OK);
    }
}
