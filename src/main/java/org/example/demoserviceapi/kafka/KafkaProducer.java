package org.example.demoserviceapi.kafka;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Retry(name = "kafkaProducer", fallbackMethod = "fallbackSend")
    @CircuitBreaker(name = "kafkaProducer", fallbackMethod = "fallbackSend")
    public void sendEvent(UserEvent event) {
        System.out.println(">> Sending event to Kafka: " + event);
        kafkaTemplate.send("topic1", event);
    }

    public void fallbackSend(UserEvent event, Throwable t) {
        System.err.println("❌ Kafka send failed after retries. Fallback triggered. Reason: " + t.getMessage());
        // Тут можно сохранять сообщение в резервную очередь или логировать
    }
}
