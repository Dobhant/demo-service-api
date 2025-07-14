package org.example.notificationservice.listener;

import org.example.notificationservice.model.UserEvent;
import org.example.notificationservice.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaUserEventListener {

    private final EmailService emailService;

    public KafkaUserEventListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(
            topics = "topic1",
            groupId = "notification-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(UserEvent event) {
        emailService.sendEmailBasedOnEvent(event);
    }
}
