package com.eventregistration.customexception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@SuppressWarnings("serial")
public class UserEmailNotFoundException extends RuntimeException{
	String message;

}
