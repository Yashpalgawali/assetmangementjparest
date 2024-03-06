package com.example.demo;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewassetmanagementWithJpaRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewassetmanagementWithJpaRestApplication.class, args);
//		IntStream.range(1, 11).forEach(e->System.err.println(e));
//		IntStream.range(1, 11).map(e->e*e).filter(e->e%2==0).forEach(e->System.out.println(e));
	
//		 List.of(1,2,4,3,5,6).stream().filter(n-> n%2==0).max((n1,n2)-> Integer.compare(n1, n2));
		
	} 
}
