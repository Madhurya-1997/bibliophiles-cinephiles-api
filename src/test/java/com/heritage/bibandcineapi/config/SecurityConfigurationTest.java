package com.heritage.bibandcineapi.config;

import com.heritage.bibandcineapi.security.JwtFilter;
import com.heritage.bibandcineapi.services.MyUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class SecurityConfigurationTest {

    @Mock
    private MyUserDetailsService mockUserDetailsService;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;
    @Mock
    private JwtFilter mockJwtFilter;

    @InjectMocks
    private SecurityConfiguration securityConfigurationUnderTest;

    @Test
    void testAuthenticationManagerBean() throws Exception {
        // Setup
        // Run the test
        final AuthenticationManager result = securityConfigurationUnderTest.authenticationManagerBean();

        // Verify the results
    }

    @Test
    void testAuthenticationManagerBean_ThrowsException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> securityConfigurationUnderTest.authenticationManagerBean())
                .isInstanceOf(Exception.class);
    }

    @Test
    void testConfigure1() throws Exception {
        // Setup
        final HttpSecurity http = new HttpSecurity(null, new AuthenticationManagerBuilder(null),
                Map.ofEntries(Map.entry(String.class, "value")));

        // Run the test
        securityConfigurationUnderTest.configure(http);

        // Verify the results
    }

    @Test
    void testConfigure1_ThrowsException() {
        // Setup
        final HttpSecurity http = new HttpSecurity(null, new AuthenticationManagerBuilder(null),
                Map.ofEntries(Map.entry(String.class, "value")));

        // Run the test
        assertThatThrownBy(() -> securityConfigurationUnderTest.configure(http)).isInstanceOf(Exception.class);
    }

    @Test
    void testCorsConfigurationSource() {
        // Setup
        // Run the test
        final CorsConfigurationSource result = securityConfigurationUnderTest.corsConfigurationSource();

        // Verify the results
    }

    @Test
    void testConfigure2() throws Exception {
        // Setup
        final WebSecurity web = new WebSecurity(null);

        // Run the test
        securityConfigurationUnderTest.configure(web);

        // Verify the results
    }

    @Test
    void testConfigure2_ThrowsException() {
        // Setup
        final WebSecurity web = new WebSecurity(null);

        // Run the test
        assertThatThrownBy(() -> securityConfigurationUnderTest.configure(web)).isInstanceOf(Exception.class);
    }
}
