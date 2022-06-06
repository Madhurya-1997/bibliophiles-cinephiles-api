package com.heritage.bibandcineapi.controller;

import com.heritage.bibandcineapi.models.Like;
import com.heritage.bibandcineapi.models.LikeResponse;
import com.heritage.bibandcineapi.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;


    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<?> addLikeCount(@PathVariable("userId")Long userId,
                                          @PathVariable("postId") Long postId,
                                          @RequestBody Like likeRequest) {

        return new ResponseEntity<LikeResponse>(likeService.addLike(userId, postId, likeRequest),
                HttpStatus.OK) ;
    }
    @DeleteMapping("/{userId}/{postId}")
    public ResponseEntity<?> deleteLikeCount(@PathVariable("userId")Long userId,
                                          @PathVariable("postId") Long postId,
                                          @RequestBody Like likeRequest) {

        return new ResponseEntity<LikeResponse>(likeService.deleteLike(userId, postId, likeRequest),
                HttpStatus.OK) ;
    }

}
