package com.heritage.bibandcineapi.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ResourceNotFoundExceptionTest {

    @Mock
    private Throwable mockCause;

    private ResourceNotFoundException resourceNotFoundExceptionUnderTest;

    @BeforeEach
    void setUp() {
        resourceNotFoundExceptionUnderTest = new ResourceNotFoundException("message", mockCause);
    }
}
