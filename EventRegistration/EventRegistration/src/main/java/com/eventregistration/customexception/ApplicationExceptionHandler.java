package com.eventregistration.customexception;

import com.eventregistration.util.ErrorStructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorStructure> excistingUserException(ExcistingUserException exception){

		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatus(HttpStatus.BAD_REQUEST.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Data Already Excist");

		return new ResponseEntity<ErrorStructure>(errorStructure,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure> userEmailNotFoundException(UserEmailNotFoundException exception){

		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatus(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Data Not Found");

		return new ResponseEntity<ErrorStructure>(errorStructure,HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure> eventIdNotFoundException(EventIdNotFoundException exception){

		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatus(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Data Not Found");

		return new ResponseEntity<ErrorStructure>(errorStructure,HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> eventDataNotFoundException(EventDataNotFoundException exception){
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatus(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("Data Not Found");

		return new ResponseEntity<ErrorStructure>(errorStructure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> unableToDeleteException(UnableToDeleteException exception){
		ErrorStructure errorStructure = new ErrorStructure();
		errorStructure.setStatus(HttpStatus.NOT_FOUND.value());
		errorStructure.setMessage(exception.getMessage());
		errorStructure.setRootCause("UserRegsitered Unable To Delete");

		return new ResponseEntity<ErrorStructure>(errorStructure,HttpStatus.NOT_FOUND);
	}

}
