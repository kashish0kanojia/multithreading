package com.example.multithreading;

import org.springframework.boot.SpringApplication;

public class TestMultithreadingApplication {

	public static void main(String[] args) {
		SpringApplication.from(MultithreadingApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
