package com.eventregistration.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eventregistration.entity.Event;
import com.eventregistration.repository.EventRepository;

@Repository
public class EventDao {

	@Autowired
	EventRepository eventRepository;
	public Event save(Event event) {
		return eventRepository.save(event);
	}
	
	public List<Event> findAll(){
		return eventRepository.findAll();
	}
	
	public Optional<Event> findById(int id){
		return eventRepository.findById(id);
	}
	
	public void deleteEvent(int eventId){
		eventRepository.deleteById(eventId);
		
	}
}
