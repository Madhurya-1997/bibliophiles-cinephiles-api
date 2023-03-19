package com.heritage.bibandcineapi.services;

import com.heritage.bibandcineapi.exception.ResourceNotFoundException;
import com.heritage.bibandcineapi.models.User;
import com.heritage.bibandcineapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImplUnderTest;

    @Test
    void testGetAllUsers() {
        // Setup
        // Configure UserRepository.findAll(...).
        final Page<User> users = new PageImpl<>(List.of(new User(0L, "username", "password", "role")));
        when(mockUserRepository.findAll(any(Pageable.class))).thenReturn(users);

        // Run the test
        final Page<User> result = userServiceImplUnderTest.getAllUsers(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    void testGetAllUsers_UserRepositoryReturnsNoItems() {
        // Setup
        when(mockUserRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final Page<User> result = userServiceImplUnderTest.getAllUsers(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    void testRegister() {
        // Setup
        final User user = new User(0L, "username", "password", "role");
        final User expectedResult = new User(0L, "username", "password", "role");
        when(mockBCryptPasswordEncoder.encode("password")).thenReturn("password");

        // Configure UserRepository.save(...).
        final User user1 = new User(0L, "username", "password", "role");
        when(mockUserRepository.save(new User(0L, "username", "password", "role"))).thenReturn(user1);

        // Run the test
        final User result = userServiceImplUnderTest.register(user);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate() {
        // Setup
        final User userRequest = new User(0L, "username", "password", "role");
        final User expectedResult = new User(0L, "username", "password", "role");

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(new User(0L, "username", "password", "role"));
        when(mockUserRepository.findById(0L)).thenReturn(user);

        // Configure UserRepository.save(...).
        final User user1 = new User(0L, "username", "password", "role");
        when(mockUserRepository.save(new User(0L, "username", "password", "role"))).thenReturn(user1);

        // Run the test
        final User result = userServiceImplUnderTest.update(0L, userRequest);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate_UserRepositoryFindByIdReturnsAbsent() {
        // Setup
        final User userRequest = new User(0L, "username", "password", "role");
        when(mockUserRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> userServiceImplUnderTest.update(0L, userRequest))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
