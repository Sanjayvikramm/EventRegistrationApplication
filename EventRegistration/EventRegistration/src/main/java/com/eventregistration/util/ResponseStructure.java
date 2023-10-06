package com.eventregistration.util;

import java.util.List;

import com.eventregistration.dto.EventResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure <T>{
	private int status;
	private String message;
	private T data;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<EventResponse> listData;
}
