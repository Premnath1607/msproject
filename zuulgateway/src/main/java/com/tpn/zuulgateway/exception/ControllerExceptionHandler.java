package com.tpn.zuulgateway.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tpn.zuulgateway.model.Constants;

import feign.FeignException;


/**
 * 
 * @author Premnath T 
 * 
 * ControllerException class used to handle global exception and custom exceptions
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

	private static final Logger logger = LogManager.getLogger(ControllerExceptionHandler.class);

	/**
	 * handleMethodArgumentNotValid method used to handle method argument validation
	 * exception
	 * 
	 * @param methodArgException
	 * @return
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgException) {
		Map<String, String> errors = new HashMap<>();
		methodArgException.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(String.valueOf(error.getField()), error.getDefaultMessage()));
		return errors;
	}
	
	/**
	 * handleMethodArgumentNotValid method used to handle method argument validation
	 * exception
	 * 
	 * @param methodArgException
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ExceptionResponse> handleBadCredentialsException(BadCredentialsException exception) {	
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode(Constants.BAD_REQUEST);
		response.setErrorMessage(exception.getMessage());
		response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * handleMethodArgumentNotValid method used to handle method argument validation
	 * exception
	 * 
	 * @param methodArgException
	 * @return
	 */
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleUserNameNotFoundException(UsernameNotFoundException exception) {	
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode(Constants.BAD_REQUEST);
		response.setErrorMessage(exception.getMessage());
		response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * handleMethodArgumentNotValid method used to handle read validation
	 * exception
	 * 
	 * @param readException
	 * @return
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ExceptionResponse> handleInvalidFormat(HttpMessageNotReadableException readException) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("FORMAT_ERROR");
		response.setErrorMessage(readException.getMessage());
		response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ExceptionResponse> handleFeignException(FeignException readException) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("BAD_REQUEST");
		response.setErrorMessage(readException.getMessage());
		response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}


	/**
	 * handleConstraintViolation method used to handle constraint validation exceptions
	 * @param constraintException
	 * @return
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handleConstraintViolation(ConstraintViolationException constraintException) {
		Map<String, String> errors = new HashMap<>();
		constraintException.getConstraintViolations().forEach(cv -> {
			errors.put("message", cv.getMessage());
			errors.put("path", (cv.getPropertyPath()).toString());
		});

		return errors;
	}

	/**
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<String> handleRunTimeException(RuntimeException exception) {
		return postError(HttpStatus.INTERNAL_SERVER_ERROR, exception);
	}

	/**
	 * 
	 * @param status
	 * @param exception
	 * @return
	 */
	private ResponseEntity<String> postError(HttpStatus status, Exception exception) {
		logger.error("Exception Detail : ", exception);
		return ResponseEntity.status(status).body(exception.getMessage());
	}
	
	/**
	 * customException method used to handle custom exceptions of service class
	 * @param customException
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ExceptionResponse> customException(CustomException customException) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("BAD_REQUEST");
		response.setErrorMessage(customException.getMessage());
		response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param resourceException
	 * @return
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> resourceNotFoundException(ResourceNotFoundException resourceException) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("NOT_FOUND");
		response.setErrorMessage(resourceException.getMessage());
		response.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}


}
