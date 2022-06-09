package com.heritage.bibandcineapi.repository;

import com.heritage.bibandcineapi.models.Like;
import com.heritage.bibandcineapi.models.Post;
import com.heritage.bibandcineapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query(value = "SELECT COUNT(l) FROM Like l WHERE l.post.id=:postId")
    Long findAllLikes(@Param("postId") Long postId);

//    @Query(value = "SELECT l from Like l WHERE l.user.id=:userId AND l.post.id=:postId")
    void deleteByUserAndPost(User user, Post post);

    Optional<Like> findTopByPostAndUserOrderByIdDesc(Post post, User currentUser);
}


