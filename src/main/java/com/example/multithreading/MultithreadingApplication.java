package com.example.multithreading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync  //Enables asynchronous processing ,
				// allows methods with @Async annotations to run in separate thread
public class MultithreadingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultithreadingApplication.class, args);
	}

}
