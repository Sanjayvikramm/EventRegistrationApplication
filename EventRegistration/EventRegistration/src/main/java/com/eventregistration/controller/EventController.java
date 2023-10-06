package com.eventregistration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eventregistration.dto.EventRequest;
import com.eventregistration.dto.EventResponse;
import com.eventregistration.service.EventService;
import com.eventregistration.util.ResponseStructure;

@RestController
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	EventService eventService;
	
	
	@PostMapping("/createEvent")
	@CrossOrigin
	public ResponseEntity<ResponseStructure<EventResponse>> create(@RequestParam String email,@RequestBody EventRequest eventRequest){
		return eventService.createEvent(email, eventRequest);
	}
	
	@PutMapping("/updateEvent")
	@CrossOrigin
		ResponseEntity<ResponseStructure<EventResponse>> updateEvent(@RequestParam int eventId,@RequestBody EventRequest eventRequest){
			return eventService.updateEvent(eventId,eventRequest);
		}
	
	@GetMapping("/openEvent")
	@CrossOrigin
	public ResponseEntity<ResponseStructure<List<EventResponse>>> openEvents(){
		return eventService.openEvent();
	}
	
	@GetMapping("/allEvent")
	@CrossOrigin
	public ResponseEntity<ResponseStructure<List<EventResponse>>> allEvents(){
		return eventService.allEvent();
	}
	
	
	@GetMapping("/myEvent")
	@CrossOrigin
	public ResponseEntity<ResponseStructure<List<EventResponse>>> myEvents(){
		return eventService.myEvent();
	}
	
	@GetMapping("/fetchEventData")
	@CrossOrigin
	public ResponseEntity<ResponseStructure<EventResponse>> fetch(@RequestParam int eventId){
		return eventService.fetchEventData(eventId);
	}
	
	@DeleteMapping("/deleteEvent")
	@CrossOrigin
	public ResponseEntity<ResponseStructure<List<EventResponse>>> deleteEvents(@RequestParam int eventId){
		return eventService.deleteEvent(eventId);
	}

}
