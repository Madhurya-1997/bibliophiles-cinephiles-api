package com.heritage.bibandcineapi.services;

import com.heritage.bibandcineapi.models.*;
import com.heritage.bibandcineapi.repository.LikeRepository;
import com.heritage.bibandcineapi.repository.PostRepository;
import com.heritage.bibandcineapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LikeServiceImplTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private PostRepository mockPostRepository;
    @Mock
    private LikeRepository mockLikeRepository;

    @InjectMocks
    private LikeServiceImpl likeServiceImplUnderTest;

    @Test
    void testAddLike() {
        // Setup
        final Like likeRequest = new Like();
        likeRequest.setId(0L);
        likeRequest.setIsLiked(false);
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        user.setPhoneNumber("phoneNumber");
        final Feedback feedback = new Feedback();
        feedback.setId(0L);
        feedback.setFeedback("feedback");
        final Post post = new Post();
        post.setId(0L);
        post.setTitle("title");
        feedback.setPost(post);
        user.setFeedback(feedback);
        likeRequest.setUser(user);
        final Post post1 = new Post();
        post1.setId(0L);
        post1.setTitle("title");
        likeRequest.setPost(post1);

        // Configure UserRepository.findById(...).
        final Optional<User> user1 = Optional.of(new User(0L, "username", "password", "role"));
        when(mockUserRepository.findById(0L)).thenReturn(user1);

        // Configure PostRepository.findById(...).
        final Optional<Post> post2 = Optional.of(
                new Post(0L, "title", "description", new User(0L, "username", "password", "role")));
        when(mockPostRepository.findById(0L)).thenReturn(post2);

        // Configure LikeRepository.save(...).
        final Like like = new Like();
        like.setId(0L);
        like.setIsLiked(false);
        final User user2 = new User();
        user2.setId(0L);
        user2.setUsername("username");
        user2.setPassword("password");
        user2.setEmail("email");
        user2.setPhoneNumber("phoneNumber");
        final Feedback feedback1 = new Feedback();
        feedback1.setId(0L);
        feedback1.setFeedback("feedback");
        final Post post3 = new Post();
        post3.setId(0L);
        post3.setTitle("title");
        feedback1.setPost(post3);
        user2.setFeedback(feedback1);
        like.setUser(user2);
        final Post post4 = new Post();
        post4.setId(0L);
        post4.setTitle("title");
        like.setPost(post4);
        when(mockLikeRepository.save(new Like())).thenReturn(like);

        when(mockLikeRepository.findAllLikes(0L)).thenReturn(0L);

        // Run the test
        final LikeResponse result = likeServiceImplUnderTest.addLike(0L, 0L, likeRequest);

        // Verify the results
    }

    @Test
    void testAddLike_UserRepositoryReturnsAbsent() {
        // Setup
        final Like likeRequest = new Like();
        likeRequest.setId(0L);
        likeRequest.setIsLiked(false);
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        user.setPhoneNumber("phoneNumber");
        final Feedback feedback = new Feedback();
        feedback.setId(0L);
        feedback.setFeedback("feedback");
        final Post post = new Post();
        post.setId(0L);
        post.setTitle("title");
        feedback.setPost(post);
        user.setFeedback(feedback);
        likeRequest.setUser(user);
        final Post post1 = new Post();
        post1.setId(0L);
        post1.setTitle("title");
        likeRequest.setPost(post1);

        when(mockUserRepository.findById(0L)).thenReturn(Optional.empty());

        // Configure PostRepository.findById(...).
        final Optional<Post> post2 = Optional.of(
                new Post(0L, "title", "description", new User(0L, "username", "password", "role")));
        when(mockPostRepository.findById(0L)).thenReturn(post2);

        // Configure LikeRepository.save(...).
        final Like like = new Like();
        like.setId(0L);
        like.setIsLiked(false);
        final User user1 = new User();
        user1.setId(0L);
        user1.setUsername("username");
        user1.setPassword("password");
        user1.setEmail("email");
        user1.setPhoneNumber("phoneNumber");
        final Feedback feedback1 = new Feedback();
        feedback1.setId(0L);
        feedback1.setFeedback("feedback");
        final Post post3 = new Post();
        post3.setId(0L);
        post3.setTitle("title");
        feedback1.setPost(post3);
        user1.setFeedback(feedback1);
        like.setUser(user1);
        final Post post4 = new Post();
        post4.setId(0L);
        post4.setTitle("title");
        like.setPost(post4);
        when(mockLikeRepository.save(new Like())).thenReturn(like);

        when(mockLikeRepository.findAllLikes(0L)).thenReturn(0L);

        // Run the test
        final LikeResponse result = likeServiceImplUnderTest.addLike(0L, 0L, likeRequest);

        // Verify the results
    }

    @Test
    void testAddLike_PostRepositoryReturnsAbsent() {
        // Setup
        final Like likeRequest = new Like();
        likeRequest.setId(0L);
        likeRequest.setIsLiked(false);
        final User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        user.setPhoneNumber("phoneNumber");
        final Feedback feedback = new Feedback();
        feedback.setId(0L);
        feedback.setFeedback("feedback");
        final Post post = new Post();
        post.setId(0L);
        post.setTitle("title");
        feedback.setPost(post);
        user.setFeedback(feedback);
        likeRequest.setUser(user);
        final Post post1 = new Post();
        post1.setId(0L);
        post1.setTitle("title");
        likeRequest.setPost(post1);

        // Configure UserRepository.findById(...).
        final Optional<User> user1 = Optional.of(new User(0L, "username", "password", "role"));
        when(mockUserRepository.findById(0L)).thenReturn(user1);

        when(mockPostRepository.findById(0L)).thenReturn(Optional.empty());

        // Configure LikeRepository.save(...).
        final Like like = new Like();
        like.setId(0L);
        like.setIsLiked(false);
        final User user2 = new User();
        user2.setId(0L);
        user2.setUsername("username");
        user2.setPassword("password");
        user2.setEmail("email");
        user2.setPhoneNumber("phoneNumber");
        final Feedback feedback1 = new Feedback();
        feedback1.setId(0L);
        feedback1.setFeedback("feedback");
        final Post post2 = new Post();
        post2.setId(0L);
        post2.setTitle("title");
        feedback1.setPost(post2);
        user2.setFeedback(feedback1);
        like.setUser(user2);
        final Post post3 = new Post();
        post3.setId(0L);
        post3.setTitle("title");
        like.setPost(post3);
        when(mockLikeRepository.save(new Like())).thenReturn(like);

        when(mockLikeRepository.findAllLikes(0L)).thenReturn(0L);

        // Run the test
        final LikeResponse result = likeServiceImplUnderTest.addLike(0L, 0L, likeRequest);

        // Verify the results
    }

    @Test
    void testDeleteLike() {
        // Setup
        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(new User(0L, "username", "password", "role"));
        when(mockUserRepository.findById(0L)).thenReturn(user);

        // Configure PostRepository.findById(...).
        final Optional<Post> post = Optional.of(
                new Post(0L, "title", "description", new User(0L, "username", "password", "role")));
        when(mockPostRepository.findById(0L)).thenReturn(post);

        when(mockLikeRepository.findAllLikes(0L)).thenReturn(0L);

        // Run the test
        final Long result = likeServiceImplUnderTest.deleteLike(0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(0L);
        verify(mockLikeRepository).deleteByUserAndPost(new User(0L, "username", "password", "role"),
                new Post(0L, "title", "description", new User(0L, "username", "password", "role")));
    }

    @Test
    void testDeleteLike_UserRepositoryReturnsAbsent() {
        // Setup
        when(mockUserRepository.findById(0L)).thenReturn(Optional.empty());

        // Configure PostRepository.findById(...).
        final Optional<Post> post = Optional.of(
                new Post(0L, "title", "description", new User(0L, "username", "password", "role")));
        when(mockPostRepository.findById(0L)).thenReturn(post);

        when(mockLikeRepository.findAllLikes(0L)).thenReturn(0L);

        // Run the test
        final Long result = likeServiceImplUnderTest.deleteLike(0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(0L);
        verify(mockLikeRepository).deleteByUserAndPost(new User(0L, "username", "password", "role"),
                new Post(0L, "title", "description", new User(0L, "username", "password", "role")));
    }

    @Test
    void testDeleteLike_PostRepositoryReturnsAbsent() {
        // Setup
        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(new User(0L, "username", "password", "role"));
        when(mockUserRepository.findById(0L)).thenReturn(user);

        when(mockPostRepository.findById(0L)).thenReturn(Optional.empty());
        when(mockLikeRepository.findAllLikes(0L)).thenReturn(0L);

        // Run the test
        final Long result = likeServiceImplUnderTest.deleteLike(0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(0L);
        verify(mockLikeRepository).deleteByUserAndPost(new User(0L, "username", "password", "role"),
                new Post(0L, "title", "description", new User(0L, "username", "password", "role")));
    }

    @Test
    void testFindAllLikesFromPost() {
        // Setup
        when(mockLikeRepository.findAllLikes(0L)).thenReturn(0L);

        // Run the test
        final Long result = likeServiceImplUnderTest.findAllLikesFromPost(0L);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }
}
