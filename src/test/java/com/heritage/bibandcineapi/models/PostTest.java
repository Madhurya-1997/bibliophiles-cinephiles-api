package com.heritage.bibandcineapi.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PostTest {

    @Mock
    private User mockUser;

    private Post postUnderTest;

    @BeforeEach
    void setUp() {
        postUnderTest = new Post(0L, "title", "description", mockUser);
    }
}
