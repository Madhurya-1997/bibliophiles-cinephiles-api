package com.heritage.bibandcineapi.services;

import com.heritage.bibandcineapi.exception.ResourceNotFoundException;
import com.heritage.bibandcineapi.models.Feedback;
import com.heritage.bibandcineapi.models.FeedbackRequest;
import com.heritage.bibandcineapi.models.Post;
import com.heritage.bibandcineapi.models.User;
import com.heritage.bibandcineapi.repository.FeedbackRepository;
import com.heritage.bibandcineapi.repository.PostRepository;
import com.heritage.bibandcineapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceImplTest {

    @Mock
    private FeedbackRepository mockFeedbackRepository;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private PostRepository mockPostRepository;
    @Mock
    private EmailServiceImpl mockEmailService;

    @InjectMocks
    private FeedbackServiceImpl feedbackServiceImplUnderTest;

    @Test
    void testAddFeedback() {
        // Setup
        final FeedbackRequest feedbackRequest = new FeedbackRequest("feedback", "userEmail");
        final Feedback expectedResult = new Feedback(0L, "feedback", new User(0L, "username", "password", "role"),
                new Post(0L, "title", "description", new User(0L, "username", "password", "role")));

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(new User(0L, "username", "password", "role"));
        when(mockUserRepository.findById(0L)).thenReturn(user);

        // Configure PostRepository.findById(...).
        final Optional<Post> post = Optional.of(
                new Post(0L, "title", "description", new User(0L, "username", "password", "role")));
        when(mockPostRepository.findById(0L)).thenReturn(post);

        // Configure FeedbackRepository.save(...).
        final Feedback feedback = new Feedback(0L, "feedback", new User(0L, "username", "password", "role"),
                new Post(0L, "title", "description", new User(0L, "username", "password", "role")));
        when(mockFeedbackRepository.save(new Feedback(0L, "feedback", new User(0L, "username", "password", "role"),
                new Post(0L, "title", "description", new User(0L, "username", "password", "role")))))
                .thenReturn(feedback);

        // Run the test
        final Feedback result = feedbackServiceImplUnderTest.addFeedback(0L, 0L, feedbackRequest);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockEmailService).sendSimpleMessage("email", "subject", "feedback");
    }

    @Test
    void testAddFeedback_UserRepositoryReturnsAbsent() {
        // Setup
        final FeedbackRequest feedbackRequest = new FeedbackRequest("feedback", "userEmail");
        when(mockUserRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> feedbackServiceImplUnderTest.addFeedback(0L, 0L, feedbackRequest))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void testAddFeedback_PostRepositoryReturnsAbsent() {
        // Setup
        final FeedbackRequest feedbackRequest = new FeedbackRequest("feedback", "userEmail");

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(new User(0L, "username", "password", "role"));
        when(mockUserRepository.findById(0L)).thenReturn(user);

        when(mockPostRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> feedbackServiceImplUnderTest.addFeedback(0L, 0L, feedbackRequest))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void testFindAllFeedbacksFromPost() {
        // Setup
        final List<Feedback> expectedResult = List.of(
                new Feedback(0L, "feedback", new User(0L, "username", "password", "role"),
                        new Post(0L, "title", "description", new User(0L, "username", "password", "role"))));

        // Configure FeedbackRepository.findAllFeedbacksFromPost(...).
        final List<Feedback> feedbacks = List.of(
                new Feedback(0L, "feedback", new User(0L, "username", "password", "role"),
                        new Post(0L, "title", "description", new User(0L, "username", "password", "role"))));
        when(mockFeedbackRepository.findAllFeedbacksFromPost(0L)).thenReturn(feedbacks);

        // Run the test
        final List<Feedback> result = feedbackServiceImplUnderTest.findAllFeedbacksFromPost(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAllFeedbacksFromPost_FeedbackRepositoryReturnsNoItems() {
        // Setup
        when(mockFeedbackRepository.findAllFeedbacksFromPost(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<Feedback> result = feedbackServiceImplUnderTest.findAllFeedbacksFromPost(0L);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
