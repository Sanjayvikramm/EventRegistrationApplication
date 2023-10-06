package com.eventregistration.customexception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
public class EventIdNotFoundException extends RuntimeException {
	private String messgae;
}
