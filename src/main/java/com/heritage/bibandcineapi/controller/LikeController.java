package com.heritage.bibandcineapi.controller;

import com.heritage.bibandcineapi.models.Like;
import com.heritage.bibandcineapi.models.LikeResponse;
import com.heritage.bibandcineapi.services.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;


    @Operation(summary = "Get all likes for a post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched all likes for a post",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Authorization Unsuccessful",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Resource not available",
                    content = @Content)
    })
    @GetMapping("/{postId}")
    public ResponseEntity<?> findAllLikes(@PathVariable("postId") Long postId) {
        return new ResponseEntity<Long>(likeService.findAllLikesFromPost(postId), HttpStatus.OK);
    }


    @Operation(summary = "Add a like to the post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Like added",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Authorization Unsuccessful",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Resource not available",
                    content = @Content)
    })
    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<?> addLikeCount(@PathVariable("userId")Long userId,
                                          @PathVariable("postId") Long postId,
                                          @RequestBody Like likeRequest) {

        return new ResponseEntity<LikeResponse>(likeService.addLike(userId, postId, likeRequest),
                HttpStatus.OK) ;
    }


    @Operation(summary = "Remove like from a post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Like removed successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401",
                    description = "Authorization Unsuccessful",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Resource not available",
                    content = @Content)
    })
    @DeleteMapping("/{userId}/{postId}")
    public ResponseEntity<?> deleteLikeCount(@PathVariable("userId")Long userId,
                                          @PathVariable("postId") Long postId) {

        return new ResponseEntity<Long>(likeService.deleteLike(userId, postId),
                HttpStatus.OK) ;
    }

}
