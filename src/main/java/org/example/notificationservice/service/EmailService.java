package org.example.notificationservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.notificationservice.model.UserEvent;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, false);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Ошибка при отправке email: " + e.getMessage(), e);
        }
    }

    public void sendEmailBasedOnEvent(UserEvent event) {
        if (event.getEmail() == null || event.getOperation() == null) {
            throw new IllegalArgumentException("Email и операция должны быть указаны.");
        }

        String subject = "Уведомление о вашем аккаунте";
        String message;

        if ("CREATE".equalsIgnoreCase(event.getOperation())) {
            message = "Здравствуйте! Ваш аккаунт на сайте был успешно создан.";
        } else if ("DELETE".equalsIgnoreCase(event.getOperation())) {
            message = "Здравствуйте! Ваш аккаунт был удалён.";
        } else {
            message = "Неизвестная операция.";
        }

        sendEmail(event.getEmail(), subject, message);
    }
}