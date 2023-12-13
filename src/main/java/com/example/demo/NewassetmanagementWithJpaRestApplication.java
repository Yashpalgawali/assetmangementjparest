package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD


@SpringBootApplication
public class NewassetmanagementWithJpaRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewassetmanagementWithJpaRestApplication.class, args);
		
	} 
}
=======
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class NewassetmanagementWithJpaRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewassetmanagementWithJpaRestApplication.class, args);
	
//		System.err.println(new BCryptPasswordEncoder().encode("admin"));
	}
 
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
>>>>>>> branch 'master' of https://github.com/Yashpalgawali/assetmangementjparest.git
