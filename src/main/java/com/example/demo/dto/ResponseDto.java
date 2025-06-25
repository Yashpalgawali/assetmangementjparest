package com.example.demo.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data @AllArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseDto {

	  String statusCode;
	  String statusMsg;
}
