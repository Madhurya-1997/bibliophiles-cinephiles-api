package com.heritage.bibandcineapi.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LikeResponseTest {

    @Mock
    private Like mockLike;

    private LikeResponse likeResponseUnderTest;

    @BeforeEach
    void setUp() {
        likeResponseUnderTest = new LikeResponse(mockLike, 0L);
    }
}
