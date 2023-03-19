package com.heritage.bibandcineapi.security;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {

    private JwtUtil jwtUtilUnderTest;

    @BeforeEach
    void setUp() {
        jwtUtilUnderTest = new JwtUtil();
    }

    @Test
    void testExtractUsername() {
        assertThat(jwtUtilUnderTest.extractUsername("token")).isEqualTo("result");
    }

    @Test
    void testExtractExpiration() {
        assertThat(jwtUtilUnderTest.extractExpiration("token"))
                .isEqualTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    }

    @Test
    void testExtractClaim() {
        // Setup
        final Function<Claims, String> claimsResolver = val -> {
            return null;
        };

        // Run the test
        final String result = jwtUtilUnderTest.extractClaim("token", claimsResolver);

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGenerateToken() {
        // Setup
        final UserDetails userDetails = null;

        // Run the test
        final String result = jwtUtilUnderTest.generateToken(userDetails);

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testCreateToken() {
        // Setup
        final Map<String, Object> claims = Map.ofEntries(Map.entry("value", "value"));

        // Run the test
        final String result = jwtUtilUnderTest.createToken(claims, "subject");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testValidateToken() {
        // Setup
        final UserDetails userDetails = null;

        // Run the test
        final Boolean result = jwtUtilUnderTest.validateToken("token", userDetails);

        // Verify the results
        assertThat(result).isFalse();
    }
}
