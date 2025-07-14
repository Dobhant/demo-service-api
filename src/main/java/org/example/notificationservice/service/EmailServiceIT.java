package org.example.notificationservice.service;

import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;
import jakarta.mail.internet.MimeMessage;
import org.example.notificationservice.model.UserEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class EmailServiceIT {

    @Autowired
    private EmailService emailService;

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP);

    @Test
    void testSendEmailBasedOnEvent_createOperation() throws Exception {
        // given
        UserEvent event = new UserEvent();
        event.setEmail("test@example.com");
        event.setOperation("CREATE");

        // when
        emailService.sendEmailBasedOnEvent(event);

        // then
        greenMail.waitForIncomingEmail(1);
        MimeMessage[] messages = greenMail.getReceivedMessages();
        assertThat(messages).hasSize(1);
        assertThat(messages[0].getSubject()).isEqualTo("Уведомление о вашем аккаунте");
        assertThat(messages[0].getAllRecipients()[0].toString()).isEqualTo("test@example.com");
        String content = (String) messages[0].getContent();
        assertThat(content).contains("Ваш аккаунт на сайте был успешно создан");
    }
}
