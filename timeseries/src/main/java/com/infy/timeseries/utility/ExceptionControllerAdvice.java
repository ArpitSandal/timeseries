package com.infy.timeseries.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.infy.timeseries.exception.TimeSeriesException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(TimeSeriesException.class)
	public ResponseEntity<String> eventRegistrationExceptionHandler(TimeSeriesException exception) {

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> generalExceptionHandler(Exception exception) {

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}