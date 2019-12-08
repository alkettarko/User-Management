package com.isolutions.usermanagement.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = UserManagementException.class)
	public ResponseEntity<Object> exception(UserManagementException exception) {
		return new ResponseEntity<>(exception.getErrorMessage(), exception.getStatus());
	}

}
