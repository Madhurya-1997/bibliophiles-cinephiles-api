package com.heritage.bibandcineapi.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LikeTest {

    private Like likeUnderTest;

    @BeforeEach
    void setUp() {
        likeUnderTest = new Like();
    }

    @Test
    void testEquals() {
        assertThat(likeUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(likeUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(likeUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    void testToString() {
        assertThat(likeUnderTest.toString()).isEqualTo("result");
    }
}
