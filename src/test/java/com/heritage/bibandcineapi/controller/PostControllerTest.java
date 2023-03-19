package com.heritage.bibandcineapi.controller;

import com.heritage.bibandcineapi.models.Post;
import com.heritage.bibandcineapi.models.User;
import com.heritage.bibandcineapi.repository.PostRepository;
import com.heritage.bibandcineapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository mockUserRepository;
    @MockBean
    private PostRepository mockPostRepository;

    @Test
    void testGetAllPosts() throws Exception {
        // Setup
        // Configure PostRepository.findAll(...).
        final Page<Post> posts = new PageImpl<>(
                List.of(new Post(0L, "title", "description", new User(0L, "username", "password", "role"))));
        when(mockPostRepository.findAll(any(Pageable.class))).thenReturn(posts);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("api/posts/path")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetAllPosts_PostRepositoryReturnsNoItems() throws Exception {
        // Setup
        when(mockPostRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("api/posts/path")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetAllPostsByUserId() throws Exception {
        // Setup
        // Configure PostRepository.findByUserId(...).
        final Page<Post> posts = new PageImpl<>(
                List.of(new Post(0L, "title", "description", new User(0L, "username", "password", "role"))));
        when(mockPostRepository.findByUserId(eq(0L), any(Pageable.class))).thenReturn(posts);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("api/posts/{userId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetAllPostsByUserId_PostRepositoryReturnsNoItems() throws Exception {
        // Setup
        when(mockPostRepository.findByUserId(eq(0L), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("api/posts/{userId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testCreatePost() throws Exception {
        // Setup
        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(new User(0L, "username", "password", "role"));
        when(mockUserRepository.findById(0L)).thenReturn(user);

        // Configure PostRepository.save(...).
        final Post post = new Post(0L, "title", "description", new User(0L, "username", "password", "role"));
        when(mockPostRepository.save(
                new Post(0L, "title", "description", new User(0L, "username", "password", "role")))).thenReturn(post);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("api/posts/{userId}", 0)
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testCreatePost_UserRepositoryReturnsAbsent() throws Exception {
        // Setup
        when(mockUserRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("api/posts/{userId}", 0)
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testUpdatePost() throws Exception {
        // Setup
        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(new User(0L, "username", "password", "role"));
        when(mockUserRepository.findById(0L)).thenReturn(user);

        // Configure PostRepository.findById(...).
        final Optional<Post> post = Optional.of(
                new Post(0L, "title", "description", new User(0L, "username", "password", "role")));
        when(mockPostRepository.findById(0L)).thenReturn(post);

        // Configure PostRepository.save(...).
        final Post post1 = new Post(0L, "title", "description", new User(0L, "username", "password", "role"));
        when(mockPostRepository.save(
                new Post(0L, "title", "description", new User(0L, "username", "password", "role")))).thenReturn(post1);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("api/posts/{userId}/{postId}", 0, 0)
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testUpdatePost_UserRepositoryReturnsAbsent() throws Exception {
        // Setup
        when(mockUserRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("api/posts/{userId}/{postId}", 0, 0)
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testUpdatePost_PostRepositoryFindByIdReturnsAbsent() throws Exception {
        // Setup
        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(new User(0L, "username", "password", "role"));
        when(mockUserRepository.findById(0L)).thenReturn(user);

        when(mockPostRepository.findById(0L)).thenReturn(Optional.empty());

        // Configure PostRepository.save(...).
        final Post post = new Post(0L, "title", "description", new User(0L, "username", "password", "role"));
        when(mockPostRepository.save(
                new Post(0L, "title", "description", new User(0L, "username", "password", "role")))).thenReturn(post);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("api/posts/{userId}/{postId}", 0, 0)
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
