package com.eventregistration.customexception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@SuppressWarnings("serial")
public class EventDataNotFoundException extends RuntimeException{
	String messgae;

}
