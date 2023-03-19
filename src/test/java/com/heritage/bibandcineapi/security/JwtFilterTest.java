package com.heritage.bibandcineapi.security;

import com.heritage.bibandcineapi.services.MyUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtFilterTest {

    @Mock
    private MyUserDetailsService mockUserDetailsService;
    @Mock
    private JwtUtil mockJwtUtil;

    @InjectMocks
    private JwtFilter jwtFilterUnderTest;

    @Test
    void testDoFilterInternal() throws Exception {
        // Setup
        final HttpServletRequest request = new MockHttpServletRequest();
        final HttpServletResponse response = new MockHttpServletResponse();
        final FilterChain filterChain = null;
        when(mockJwtUtil.extractUsername("token")).thenReturn("username");
        when(mockUserDetailsService.loadUserByUsername("username")).thenReturn(null);
        when(mockJwtUtil.validateToken(eq("token"), any(UserDetails.class))).thenReturn(false);

        // Run the test
        jwtFilterUnderTest.doFilterInternal(request, response, filterChain);

        // Verify the results
    }

    @Test
    void testDoFilterInternal_MyUserDetailsServiceThrowsUsernameNotFoundException() {
        // Setup
        final HttpServletRequest request = new MockHttpServletRequest();
        final HttpServletResponse response = new MockHttpServletResponse();
        final FilterChain filterChain = null;
        when(mockJwtUtil.extractUsername("token")).thenReturn("username");
        when(mockUserDetailsService.loadUserByUsername("username")).thenThrow(UsernameNotFoundException.class);

        // Run the test
        assertThatThrownBy(() -> jwtFilterUnderTest.doFilterInternal(request, response, filterChain))
                .isInstanceOf(UsernameNotFoundException.class);
    }
}
