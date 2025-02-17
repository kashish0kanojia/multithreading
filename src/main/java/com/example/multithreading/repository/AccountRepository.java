package com.example.multithreading.repository;

import com.example.multithreading.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>
        /**
         * JpaRepository<Account, Long> is a Spring Data JPA repository interface
         that provides built-in CRUD (Create, Read, Update, Delete) operations
        for managing Account entities with a primary key of type Long.
         **/
    {

    }
