package com.example.demo.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponseDto {

	String apiPath;
	HttpStatus errorCode;
	String errorMessage;
	LocalDateTime errorTime;	
}
