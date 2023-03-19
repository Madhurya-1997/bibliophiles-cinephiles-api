package com.heritage.bibandcineapi.controller;

import com.heritage.bibandcineapi.models.Feedback;
import com.heritage.bibandcineapi.models.FeedbackRequest;
import com.heritage.bibandcineapi.models.Post;
import com.heritage.bibandcineapi.models.User;
import com.heritage.bibandcineapi.services.FeedbackService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FeedbackController.class)
class FeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeedbackService mockFeedbackService;

    @Test
    void testGetAllFeedbacksFromPost() throws Exception {
        // Setup
        // Configure FeedbackService.findAllFeedbacksFromPost(...).
        final List<Feedback> feedbacks = List.of(
                new Feedback(0L, "feedback", new User(0L, "username", "password", "role"),
                        new Post(0L, "title", "description", new User(0L, "username", "password", "role"))));
        when(mockFeedbackService.findAllFeedbacksFromPost(0L)).thenReturn(feedbacks);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/feedback/{postId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetAllFeedbacksFromPost_FeedbackServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockFeedbackService.findAllFeedbacksFromPost(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/feedback/{postId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testPostFeedback() throws Exception {
        // Setup
        // Configure FeedbackService.addFeedback(...).
        final Feedback feedback = new Feedback(0L, "feedback", new User(0L, "username", "password", "role"),
                new Post(0L, "title", "description", new User(0L, "username", "password", "role")));
        when(mockFeedbackService.addFeedback(0L, 0L, new FeedbackRequest("feedback", "userEmail")))
                .thenReturn(feedback);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/feedback/{userId}/{postId}", 0, 0)
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
