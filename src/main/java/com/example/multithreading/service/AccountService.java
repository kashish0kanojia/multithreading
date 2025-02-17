package com.example.multithreading.service;

import com.example.multithreading.model.Account;
import com.example.multithreading.repository.AccountRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccountService {
    /*
    *   @Service → Marks this class as a business service layer.
	•	@Async → Runs createAccount() asynchronously in a separate thread.
	•	CompletableFuture<Account>:
	•	Ensures non-blocking execution.
	•	The response is returned once the account is saved in the database.
    *
    * */
    private final AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Async
    public CompletableFuture<Account> createAccount(String name) {
        Account account = new Account();
        account.setName(name);
        return CompletableFuture.completedFuture(accountRepository.save(account));
    }
}
