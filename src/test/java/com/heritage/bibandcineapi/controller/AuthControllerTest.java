package com.heritage.bibandcineapi.controller;

import com.heritage.bibandcineapi.models.User;
import com.heritage.bibandcineapi.repository.UserRepository;
import com.heritage.bibandcineapi.security.JwtUtil;
import com.heritage.bibandcineapi.services.MyUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager mockManager;
    @MockBean
    private MyUserDetailsService mockUserDetailsService;
    @MockBean
    private UserRepository mockUserRepository;
    @MockBean
    private JwtUtil mockJwtUtil;

    @Test
    void testCreateAuthenticationToken() throws Exception {
        // Setup
        when(mockManager.authenticate(null)).thenReturn(null);
        when(mockUserDetailsService.loadUserByUsername("principal")).thenReturn(null);
        when(mockJwtUtil.generateToken(any(UserDetails.class))).thenReturn("jwt");

        // Configure UserRepository.findUserByUsername(...).
        final Optional<User> user = Optional.of(new User(0L, "username", "password", "role"));
        when(mockUserRepository.findUserByUsername("username")).thenReturn(user);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/authenticate")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockManager).authenticate(null);
    }

    @Test
    void testCreateAuthenticationToken_AuthenticationManagerThrowsAuthenticationException() throws Exception {
        // Setup
        when(mockManager.authenticate(null)).thenThrow(AuthenticationException.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/authenticate")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testCreateAuthenticationToken_MyUserDetailsServiceThrowsUsernameNotFoundException() throws Exception {
        // Setup
        when(mockManager.authenticate(null)).thenReturn(null);
        when(mockUserDetailsService.loadUserByUsername("principal")).thenThrow(UsernameNotFoundException.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/authenticate")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockManager).authenticate(null);
    }

    @Test
    void testCreateAuthenticationToken_UserRepositoryReturnsAbsent() throws Exception {
        // Setup
        when(mockManager.authenticate(null)).thenReturn(null);
        when(mockUserDetailsService.loadUserByUsername("principal")).thenReturn(null);
        when(mockJwtUtil.generateToken(any(UserDetails.class))).thenReturn("jwt");
        when(mockUserRepository.findUserByUsername("username")).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/authenticate")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockManager).authenticate(null);
    }
}
