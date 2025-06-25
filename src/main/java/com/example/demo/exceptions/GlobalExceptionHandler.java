package com.example.demo.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

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
	
	@ExceptionHandler(GlobalException.class)
	public ResponseEntity<ErrorResponseDto> handleGlobalException(GlobalException exception, WebRequest request) {
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
													request.getDescription(false),
													HttpStatus.INTERNAL_SERVER_ERROR,
													exception.getMessage(),
													LocalDateTime.now()
				);
		return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
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
	
	// You are returning the response with status code 304 Not Modified, but HTTP 304 is a special status used for caching/conditional GETs, and Angular's HttpClient automatically suppresses the response body for such statuses.
	// RFC 7232: 304 Not Modified should not contain a response body. Browsers and clients follow this strictly.

	@ExceptionHandler(ResourceNotModifiedException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotModifiedException(ResourceNotModifiedException exception, WebRequest request) {
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
													request.getDescription(false),
													HttpStatus.CONFLICT,
													exception.getMessage(),
													LocalDateTime.now()
				);
		return new ResponseEntity<>(errorResponseDto, HttpStatus.CONFLICT);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String , String> validationErrors = new HashMap<>();
		List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();
		
		validationErrorList.forEach((error)->{
			String fieldName = ((FieldError)error).getField();
			String validationMsg = error.getDefaultMessage();
			validationErrors.put(fieldName, validationMsg);
			
		});

		return new ResponseEntity<>(validationErrors,HttpStatus.BAD_REQUEST);
	}
}
