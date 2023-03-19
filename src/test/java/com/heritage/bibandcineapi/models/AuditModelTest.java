package com.heritage.bibandcineapi.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuditModelTest {

    private AuditModel auditModelUnderTest;

    @BeforeEach
    void setUp() {
        auditModelUnderTest = new AuditModel();
    }

    @Test
    void testEquals() {
        assertThat(auditModelUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(auditModelUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(auditModelUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    void testToString() {
        assertThat(auditModelUnderTest.toString()).isEqualTo("result");
    }
}
