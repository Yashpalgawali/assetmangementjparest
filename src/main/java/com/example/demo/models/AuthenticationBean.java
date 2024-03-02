package com.example.demo.models;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationBean {

	private String message;
	

//    @Value("${spring.jackson.time-zone}")
//    private StringBuffer jacksonTimeZone;
//
//    @PostConstruct
//    public void checkTimeZone() {
//        System.out.println("Jackson Timezone: " + jacksonTimeZone);
//    }
}
