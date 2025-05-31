package com.example.demo.springconfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
//@EnableSwagger2
public class SwaggerConfig  {
	
	@Value("${spring.application.name}")
	private String applicationName;
	
//	@Bean
//    Docket swaggerApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any()) // You can narrow this to a specific package
//                .paths(PathSelectors.any())
//                .build()
//                .pathMapping("/");
//    }
	
//	@Bean
//    OpenAPI customOpenAPI(@Value("${spring.application.name}") String applicationName ) {
//     return new OpenAPI()
//          .info(new Info()
//          .title("sample application API"));
//    }
}