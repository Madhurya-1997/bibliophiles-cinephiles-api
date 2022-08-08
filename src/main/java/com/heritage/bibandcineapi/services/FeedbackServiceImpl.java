package com.heritage.bibandcineapi.services;

import com.heritage.bibandcineapi.exception.ResourceNotFoundException;
import com.heritage.bibandcineapi.models.Feedback;
import com.heritage.bibandcineapi.models.FeedbackRequest;
import com.heritage.bibandcineapi.models.Post;
import com.heritage.bibandcineapi.models.User;
import com.heritage.bibandcineapi.repository.FeedbackRepository;
import com.heritage.bibandcineapi.repository.PostRepository;
import com.heritage.bibandcineapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FeedbackServiceImpl implements FeedbackService{
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private EmailServiceImpl emailService;

    @Override
    public Feedback addFeedback(Long userId, Long postId, FeedbackRequest feedbackRequest) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        Feedback feedback = new Feedback();
        feedback.setFeedback(feedbackRequest.getFeedback());
        feedback.setUser(optionalUser.get());
        Post post = postRepository.findById(postId).get();
        feedback.setPost(post);

        Feedback postedFeedback = feedbackRepository.save(feedback);
        emailService.sendSimpleMessage(optionalUser.get().getEmail(),
                "You got a feedback from " + optionalUser.get().getUsername(),
                postedFeedback.getFeedback());

        return postedFeedback;
    }

    @Override
    public List<Feedback> findAllFeedbacksFromPost(Long postId) {
        return feedbackRepository.findAllFeedbacksFromPost(postId);
    }


}
