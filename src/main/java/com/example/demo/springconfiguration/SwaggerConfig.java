package com.example.demo.springconfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class SwaggerConfig  {
	
	@Value("${spring.application.name}")
	private String applicationName;
	
	@Bean
    OpenAPI customOpenAPI(@Value("${spring.application.name}") String applicationName ) {
     return new OpenAPI()
          .info(new Info()
          .title("sample application API"));
    }
}