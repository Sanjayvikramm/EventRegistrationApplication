package com.eventregistration.customexception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@SuppressWarnings("serial")
public class UnableToDeleteException extends RuntimeException{
private String message;
}
