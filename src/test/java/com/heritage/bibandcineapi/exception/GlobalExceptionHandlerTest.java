package com.heritage.bibandcineapi.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandlerUnderTest;

    @BeforeEach
    void setUp() {
        globalExceptionHandlerUnderTest = new GlobalExceptionHandler();
    }

    @Test
    void testHandleValidationExceptions() {
        // Setup
        final MethodArgumentNotValidException ex = new MethodArgumentNotValidException(new MethodParameter( null),
                null);
        final Map<String, String> expectedResult = Map.ofEntries(Map.entry("value", "value"));

        // Run the test
        final Map<String, String> result = globalExceptionHandlerUnderTest.handleValidationExceptions(ex);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
