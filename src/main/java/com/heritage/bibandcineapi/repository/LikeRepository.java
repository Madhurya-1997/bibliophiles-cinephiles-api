package com.heritage.bibandcineapi.repository;

import com.heritage.bibandcineapi.models.Like;
import com.heritage.bibandcineapi.models.Post;
import com.heritage.bibandcineapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("SELECT count(*) from Like l WHERE l.postId=:postId and l.isLiked=:isLiked")
    Integer findAllLikes(@Param("postId") Long postId, @Param("isLiked") Boolean isLiked);

    Optional<Like> findTopByPostAndUserOrderByLikeIdDesc(Post post, User currentUser);
}


