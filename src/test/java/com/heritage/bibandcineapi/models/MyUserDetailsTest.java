package com.heritage.bibandcineapi.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyUserDetailsTest {

    @Mock
    private User mockUser;

    private MyUserDetails myUserDetailsUnderTest;

    @BeforeEach
    void setUp() {
        myUserDetailsUnderTest = new MyUserDetails(mockUser);
    }

    @Test
    void testGetAuthorities() {
        // Setup
        when(mockUser.getRole()).thenReturn("role");

        // Run the test
        final Collection<? extends GrantedAuthority> result = myUserDetailsUnderTest.getAuthorities();

        // Verify the results
    }

    @Test
    void testGetPassword() {
        // Setup
        when(mockUser.getPassword()).thenReturn("password");

        // Run the test
        final String result = myUserDetailsUnderTest.getPassword();

        // Verify the results
        assertThat(result).isEqualTo("password");
    }

    @Test
    void testGetUsername() {
        // Setup
        when(mockUser.getUsername()).thenReturn("username");

        // Run the test
        final String result = myUserDetailsUnderTest.getUsername();

        // Verify the results
        assertThat(result).isEqualTo("username");
    }

    @Test
    void testIsAccountNonExpired() {
        assertThat(myUserDetailsUnderTest.isAccountNonExpired()).isTrue();
    }

    @Test
    void testIsAccountNonLocked() {
        assertThat(myUserDetailsUnderTest.isAccountNonLocked()).isTrue();
    }

    @Test
    void testIsCredentialsNonExpired() {
        assertThat(myUserDetailsUnderTest.isCredentialsNonExpired()).isTrue();
    }

    @Test
    void testIsEnabled() {
        assertThat(myUserDetailsUnderTest.isEnabled()).isTrue();
    }
}
