package com.eventregistration.customexception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
public class ExcistingUserException extends RuntimeException {
	private String message;

}
