package com.example.multithreading.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AccountListenerLogging {
    @KafkaListener(topics = "account-topic", groupId = "logging")
    @Async
    public void listenForLogging(String message) {
        System.out.println("[Logging Group] Received Kafka Message: " + message);
    }
}
