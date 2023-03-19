package com.heritage.bibandcineapi.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private User userUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        userUnderTest = new User(0L, "username", "password", "role");
    }

    @Test
    void testSetRole() {
        // Setup
        // Run the test
        userUnderTest.setRole();

        // Verify the results
    }
}
