package com.heritage.bibandcineapi.controller;

import com.heritage.bibandcineapi.models.User;
import com.heritage.bibandcineapi.services.UserService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockUserService;

    @Test
    void testGetAllUsers() throws Exception {
        // Setup
        // Configure UserService.getAllUsers(...).
        final Page<User> users = new PageImpl<>(List.of(new User(0L, "username", "password", "role")));
        when(mockUserService.getAllUsers(any(Pageable.class))).thenReturn(users);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("api/users/")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetAllUsers_UserServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.getAllUsers(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("api/users/")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testRegister() throws Exception {
        // Setup
        // Configure UserService.register(...).
        final User user = new User(0L, "username", "password", "role");
        when(mockUserService.register(new User(0L, "username", "password", "role"))).thenReturn(user);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("api/users/register")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testUpdateUser() throws Exception {
        // Setup
        // Configure UserService.update(...).
        final User user = new User(0L, "username", "password", "role");
        when(mockUserService.update(0L, new User(0L, "username", "password", "role"))).thenReturn(user);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("api/users/{userId}", 0)
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
