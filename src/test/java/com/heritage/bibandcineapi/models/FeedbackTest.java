package com.heritage.bibandcineapi.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FeedbackTest {

    @Mock
    private User mockUser;
    @Mock
    private Post mockPost;

    private Feedback feedbackUnderTest;

    @BeforeEach
    void setUp() {
        feedbackUnderTest = new Feedback(0L, "feedback", mockUser, mockPost);
    }

    @Test
    void testEquals() {
        assertThat(feedbackUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(feedbackUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(feedbackUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    void testToString() {
        assertThat(feedbackUnderTest.toString()).isEqualTo("result");
    }
}
