package com.example.demo.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.models.Users;
import com.example.demo.repository.UsersRepository;

@Configuration
public class DataInitializer   {

	  @Bean
	  CommandLineRunner initAdminUser(UsersRepository userrepo, PasswordEncoder passwordEncoder) {
		 
		  return args -> {
			
			  if(userrepo.findByUsername("admin").isEmpty()) {
				  Users admin = new Users();
				   admin.setUsername("admin");
	                admin.setEmail("crankyash@gmail.com");
	                admin.setEnabled(1);
	                admin.setPassword(passwordEncoder.encode("admin")); // Don't store plain text passwords!
	                admin.setRole("ROLE_ADMIN");
	                userrepo.save(admin);
	                System.out.println("Admin user created: admin / admin123");
			  }
			  else {
	                System.out.println("Admin user already exists.");
			  }
		  };

	}
}
