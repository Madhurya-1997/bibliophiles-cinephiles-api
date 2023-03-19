package com.heritage.bibandcineapi.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

    @Mock
    private JavaMailSender mockMailSender;

    @InjectMocks
    private EmailServiceImpl emailServiceImplUnderTest;

    @Test
    void testSendSimpleMessage() {
        // Setup
        // Run the test
        emailServiceImplUnderTest.sendSimpleMessage("to", "subject", "text");

        // Verify the results
        verify(mockMailSender).send(new SimpleMailMessage());
    }

    @Test
    void testSendSimpleMessage_JavaMailSenderThrowsMailException() {
        // Setup
        doThrow(MailException.class).when(mockMailSender).send(new SimpleMailMessage());

        // Run the test
        assertThatThrownBy(() -> emailServiceImplUnderTest.sendSimpleMessage("to", "subject", "text"))
                .isInstanceOf(MailException.class);
    }
}
