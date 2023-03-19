package com.heritage.bibandcineapi.models;

import org.junit.jupiter.api.BeforeEach;

class AuthResponseTest {

    private AuthResponse authResponseUnderTest;

    @BeforeEach
    void setUp() {
        authResponseUnderTest = new AuthResponse("jwt", "username", "email");
    }
}
