package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class GlobalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4274461446568155848L;

	public GlobalException(String msg) {
		super(msg);
	}
}
