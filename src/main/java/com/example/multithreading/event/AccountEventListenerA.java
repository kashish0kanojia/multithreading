package com.example.multithreading.event;

import com.example.multithreading.model.AccountEvent;
import com.example.multithreading.repository.AccountEventRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/*
*       Component → Marks this class as a Spring-managed component.
	•	@KafkaListener(topics = "account-topic", groupId = "account-group"):
	•	Listens to Kafka topic "account-topic".
	•	Messages are processed in a separate consumer group.
	•	@Async → Ensures Kafka messages are processed asynchronously.
* */
@Component
public class AccountEventListenerA {
    private final AccountEventRepository accountEventRepository;

    public AccountEventListenerA(AccountEventRepository accountEventRepository) {
        this.accountEventRepository = accountEventRepository;
    }
    @KafkaListener(topics = "account-topic", groupId = "account-group", properties = "auto.offset.reset=earliest")
    @Async
    public void listenAccountCreation(String message) {
        System.out.println("[Consumer A] Received Kafka Message: " + message);

        // Save event to database
        AccountEvent event = new AccountEvent();
        event.setMessage(message);
        accountEventRepository.save(event);
    }
}
