package com.example.multithreading.controller;

import com.example.multithreading.model.Account;
import com.example.multithreading.model.AccountEvent;
import com.example.multithreading.service.AccountService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

/*
*       @RestController → Defines this class as a REST API controller.
	•	@RequestMapping("/api/account") → API base URL is /api/account.
	•	KafkaTemplate<String, String> → Used to send messages to Kafka.
	•	@PostMapping("/create"):
	•	Calls AccountService.createAccount(), which returns a CompletableFuture (non-blocking).
	•	Sends an event to Kafka when an account is created.
* */
@RestController
@RequestMapping("/api/account/")
public class AccountController {
    private final AccountService accountService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public AccountController(AccountService accountService, KafkaTemplate<String, String> kafkaTemplate) {
        this.accountService = accountService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/create")
    public CompletableFuture<Account> createAccount(@RequestParam String name) {
        return accountService.createAccount(name).thenApply(account -> {
            if (account != null && account.getId() != null) { // Ensure account is not null before calling getId()
                kafkaTemplate.send("account-topic", "Account Created: " + account.getId());
            }
            return account;
        });
    }

    @PostMapping("/createWithJson")
    public CompletableFuture<Account> createAccountFromBody(@RequestBody Account account) {
        return accountService.createAccount(account.getName()).thenApply(createdAccount -> {
            if (createdAccount != null && createdAccount.getId() != null) { // Ensure account is not null before calling getId()
                kafkaTemplate.send("account-topic", "Account Created: "
                        + createdAccount.getId());
            }
            return createdAccount;
        });
    }
}
