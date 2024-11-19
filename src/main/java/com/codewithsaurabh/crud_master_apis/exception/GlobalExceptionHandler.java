package com.codewithsaurabh.crud_master_apis.exception;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.codewithsaurabh.crud_master_apis.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException ex) {
		List<String> errors = new ArrayList<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.add(error.getField() + ": " + error.getDefaultMessage()));
		return ResponseEntity.badRequest().body(new ApiResponse("Validation Faild", false, errors));
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(ex.getMessage(), false, null));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiResponse> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException ex) {
		String errorMessage = "Parameter '" + ex.getName() + "' must be of type "
				+ ex.getRequiredType().getSimpleName();
		return ResponseEntity.badRequest().body(new ApiResponse(errorMessage, false, null));
	}

	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ApiResponse> handleNullPointerException(NullPointerException ex) {
		String errorMessage = "NullPointerException Occurred: " + ex.getMessage();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(errorMessage, false, null));

	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ApiResponse> handleException(Exception ex) {
		ApiResponse response = new ApiResponse("Internal Server Error", false, null);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {

		if (ex.getCause() instanceof ConstraintViolationException) {
			ConstraintViolationException consVioExp = (ConstraintViolationException) ex.getCause();
			String constraintName = consVioExp.getConstraintName();
			if (constraintName != null && constraintName.toLowerCase().contains("username")
					|| constraintName.toLowerCase().contains("email")) {
				return ResponseEntity.badRequest()
						.body(new ApiResponse("Username or Email already in use", false, null));
			}
		}

		return ResponseEntity.badRequest().body(new ApiResponse("Data Integrity Violation", false, null));
	}

}
