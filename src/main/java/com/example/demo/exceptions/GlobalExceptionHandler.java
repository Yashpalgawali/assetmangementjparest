package com.example.demo.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7243509103644399166L;

	@ExceptionHandler(EmployeeAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleEmployeeAlreadyExistsException(EmployeeAlreadyExistsException exception, WebRequest request) {
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
													request.getDescription(false),
													HttpStatus.BAD_REQUEST,
													exception.getMessage(),
													LocalDateTime.now()
				);
		return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
													request.getDescription(false),
													HttpStatus.NOT_FOUND,
													exception.getMessage(),
													LocalDateTime.now()
				);
		return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceNotModifiedException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotModifiedException(ResourceNotModifiedException exception , WebRequest request) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
					request.getDescription(false),
					HttpStatus.NOT_MODIFIED,
					exception.getMessage(),
					LocalDateTime.now()
				);
		return new ResponseEntity<ErrorResponseDto>(errorResponseDto, HttpStatus.NOT_MODIFIED);
	}
}
