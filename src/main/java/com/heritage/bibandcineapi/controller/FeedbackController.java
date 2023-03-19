package com.heritage.bibandcineapi.controller;

import com.heritage.bibandcineapi.models.Feedback;
import com.heritage.bibandcineapi.models.FeedbackRequest;
import com.heritage.bibandcineapi.services.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Operation(summary = "Get all feedbacks for a post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched all feedbacks",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Resource not available",
                    content = @Content)
    })
    @GetMapping("/{postId}")
    public ResponseEntity<List<Feedback>> getAllFeedbacksFromPost(@PathVariable("postId")Long postId) {
        return new ResponseEntity<List<Feedback>>(feedbackService.findAllFeedbacksFromPost(postId), HttpStatus.OK);
    }


    @Operation(summary = "Create a new feedback for a post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Created feedback",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Resource not available",
                    content = @Content)
    })
    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<Feedback> postFeedback(@PathVariable("userId") Long userId,
                                          @PathVariable("postId")Long postId,
                                          @RequestBody FeedbackRequest feedbackRequest) {
        return new ResponseEntity<Feedback>(feedbackService.addFeedback(userId, postId, feedbackRequest), HttpStatus.OK);
    }
}
