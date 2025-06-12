package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NewassetmanagementWithJpaRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewassetmanagementWithJpaRestApplication.class, args);
		
	}
}
