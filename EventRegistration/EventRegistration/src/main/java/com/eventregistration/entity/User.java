package com.eventregistration.entity;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int userId;
	private String userName;
	private String password;
	
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Event> event;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Event> registeredEvents;
}
