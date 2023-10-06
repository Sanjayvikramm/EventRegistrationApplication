package com.eventregistration.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventResponse {
	private int id;
	private String eventName;
	private String eventDescription;
	private String eventDuration;
	private String eventLocation;
	private double eventFees;
	private int eventMaxParticipants;
	private String eventTags;
}
