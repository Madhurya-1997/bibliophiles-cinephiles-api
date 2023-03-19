package com.heritage.bibandcineapi.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthRequestTest {

    private AuthRequest authRequestUnderTest;

    @BeforeEach
    void setUp() {
        authRequestUnderTest = new AuthRequest("username", "password");
    }

    @Test
    void testToString() {
        assertThat(authRequestUnderTest.toString()).isEqualTo("result");
    }
}
