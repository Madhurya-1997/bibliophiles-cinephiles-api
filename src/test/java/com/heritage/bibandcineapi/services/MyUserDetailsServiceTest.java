package com.heritage.bibandcineapi.services;

import com.heritage.bibandcineapi.models.User;
import com.heritage.bibandcineapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    @InjectMocks
    private MyUserDetailsService myUserDetailsServiceUnderTest;

    @Test
    void testLoadUserByUsername() {
        // Setup
        // Configure UserRepository.findUserByUsername(...).
        final Optional<User> user = Optional.of(new User(0L, "username", "password", "role"));
        when(mockUserRepository.findUserByUsername("username")).thenReturn(user);

        // Run the test
        final UserDetails result = myUserDetailsServiceUnderTest.loadUserByUsername("username");

        // Verify the results
    }

    @Test
    void testLoadUserByUsername_UserRepositoryReturnsAbsent() {
        // Setup
        when(mockUserRepository.findUserByUsername("username")).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> myUserDetailsServiceUnderTest.loadUserByUsername("username"))
                .isInstanceOf(UsernameNotFoundException.class);
    }

    @Test
    void testLoadUserByUsername_ThrowsUsernameNotFoundException() {
        // Setup
        // Configure UserRepository.findUserByUsername(...).
        final Optional<User> user = Optional.of(new User(0L, "username", "password", "role"));
        when(mockUserRepository.findUserByUsername("username")).thenReturn(user);

        // Run the test
        assertThatThrownBy(() -> myUserDetailsServiceUnderTest.loadUserByUsername("username"))
                .isInstanceOf(UsernameNotFoundException.class);
    }
}
