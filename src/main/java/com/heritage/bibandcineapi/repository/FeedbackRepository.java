package com.heritage.bibandcineapi.repository;

import com.heritage.bibandcineapi.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {


    @Query("SELECT f from Feedback f WHERE f.post.id=:postId")
    List<Feedback> findAllFeedbacksFromPost(@Param("postId") Long postId);

}
