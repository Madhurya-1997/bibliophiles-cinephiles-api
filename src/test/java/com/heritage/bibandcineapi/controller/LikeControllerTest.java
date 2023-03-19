package com.heritage.bibandcineapi.controller;

import com.heritage.bibandcineapi.models.*;
import com.heritage.bibandcineapi.services.LikeService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LikeController.class)
class LikeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LikeService mockLikeService;

    @Test
    void testFindAllLikes() throws Exception {
        // Setup
        when(mockLikeService.findAllLikesFromPost(0L)).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/likes/{postId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testAddLikeCount() throws Exception {
        // Setup
        // Configure LikeService.addLike(...).
        final Like like = new Like();
        like.setId(0L);
        like.setIsLiked(false);
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
        post.setDescription("description");
        feedback.setPost(post);
        user.setFeedback(feedback);
        like.setUser(user);
        final LikeResponse likeResponse = new LikeResponse(like, 0L);
        when(mockLikeService.addLike(0L, 0L, new Like())).thenReturn(likeResponse);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/likes/{userId}/{postId}", 0, 0)
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDeleteLikeCount() throws Exception {
        // Setup
        when(mockLikeService.deleteLike(0L, 0L)).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/api/likes/{userId}/{postId}", 0, 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
