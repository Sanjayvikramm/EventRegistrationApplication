package com.eventregistration.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventRequest {
	private String eventName;
	private String eventDescription;
	private String eventDuration;
	private String eventLocation;
	private double eventFees;
	private int eventMaxParticipants;
	private String eventTags;
}
