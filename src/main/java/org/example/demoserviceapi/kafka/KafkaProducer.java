package org.example.demoserviceapi.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, UserEvent> kafkaTemplate;


    public KafkaProducer(KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(UserEvent event) {
        kafkaTemplate.send("topic1", event);
    }

    public void sendEvent(UserEvent event) {
        System.out.println(">> Sending event to Kafka: " + event);
        kafkaTemplate.send("topic1", event);
    }
}
