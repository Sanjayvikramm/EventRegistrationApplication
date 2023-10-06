package com.eventregistration.entity;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Event {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int EventId;
	private String eventName;
	private String eventDescription;
	private String eventDuration;
	private String eventLocation;
	private double eventFees;
	private int eventMaxParticipants;
	private String eventTags;
	
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy="registeredEvents")
	private List<User> registeredUser;
}
