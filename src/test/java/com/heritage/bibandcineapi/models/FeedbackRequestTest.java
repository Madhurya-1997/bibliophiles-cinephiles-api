package com.heritage.bibandcineapi.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FeedbackRequestTest {

    private FeedbackRequest feedbackRequestUnderTest;

    @BeforeEach
    void setUp() {
        feedbackRequestUnderTest = new FeedbackRequest("feedback", "userEmail");
    }

    @Test
    void testEquals() {
        assertThat(feedbackRequestUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(feedbackRequestUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(feedbackRequestUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    void testToString() {
        assertThat(feedbackRequestUnderTest.toString()).isEqualTo("result");
    }
}
