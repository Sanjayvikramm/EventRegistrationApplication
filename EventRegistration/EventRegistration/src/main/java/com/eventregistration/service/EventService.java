package com.eventregistration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventregistration.customexception.EventDataNotFoundException;
import com.eventregistration.customexception.EventIdNotFoundException;
import com.eventregistration.customexception.UnableToDeleteException;
import com.eventregistration.customexception.UserEmailNotFoundException;
import com.eventregistration.dao.EventDao;
import com.eventregistration.dao.UserDao;
import com.eventregistration.dto.EventRequest;
import com.eventregistration.dto.EventResponse;
import com.eventregistration.entity.Event;
import com.eventregistration.entity.User;
import com.eventregistration.repository.EventRepository;
import com.eventregistration.repository.UserRepository;
import com.eventregistration.util.ResponseStructure;

@Service
public class EventService {

	@Autowired
	UserDao userDao;

	@Autowired
	EventDao eventDao;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	EventRepository eventRepository;

	@Autowired
	UserRepository userRepository;

	private String email;


	public ResponseEntity<ResponseStructure<EventResponse>> createEvent( String email,EventRequest eventRequest){
		Optional<User> optionalUser= userDao.findByEmail(email);

		if(optionalUser.isPresent()) {
			User user = optionalUser.get();

			Event event = this.modelMapper.map(eventRequest, Event.class);

			user.getEvent().add(event);
			event.setUser(user);

			event=eventDao.save(event);

			EventResponse eventResponse = this.modelMapper.map(event, EventResponse.class);

			ResponseStructure<EventResponse> responseStructure = new ResponseStructure<EventResponse>();
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Event Created");
			responseStructure.setData(eventResponse);

			return new ResponseEntity<ResponseStructure<EventResponse>>(responseStructure,HttpStatus.CREATED);
		}
		throw new UserEmailNotFoundException("User Not Found");
	}
	//done

	public ResponseEntity<ResponseStructure<EventResponse>> updateEvent(int eventId,EventRequest eventRequest){

		Optional<Event> optionalEvent = eventDao.findById(eventId);

		if(optionalEvent.isPresent()) {
			Event event = optionalEvent.get();
			User user = event.getUser();
			event = this.modelMapper.map(eventRequest,event.getClass());
			event.setEventId(eventId);
			event.setUser(user);
			event=eventDao.save(event);

			EventResponse eventResponse = this.modelMapper.map(event, EventResponse.class);

			ResponseStructure<EventResponse> responseStructure = new ResponseStructure<EventResponse>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Event Data Updated");
			responseStructure.setData(eventResponse);

			return new ResponseEntity<ResponseStructure<EventResponse>>(responseStructure,HttpStatus.OK);


		}
		throw new EventIdNotFoundException("Event Id Not Found");
	}
	//done
	
	public ResponseEntity<ResponseStructure<List<EventResponse>>> allEvent(){
		List<Event> listEvent = eventDao.findAll();
		
		if(listEvent==null) {
			throw new EventDataNotFoundException("Event Data Not Found");
		}
		
		List<EventResponse> listeventResponse = new ArrayList<EventResponse>();
		
		for(Event event:listEvent) {
				EventResponse eventResponse = this.modelMapper.map(event, EventResponse.class);
				listeventResponse.add(eventResponse);
		}
		
		ResponseStructure<List<EventResponse>> responseStructure = new ResponseStructure<List<EventResponse>>();
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("Event Data Found");
		responseStructure.setListData(listeventResponse);

		return new ResponseEntity<ResponseStructure<List<EventResponse>>>(responseStructure,HttpStatus.FOUND);
		
		
	}

	public ResponseEntity<ResponseStructure<List<EventResponse>>> openEvent(){
			System.out.println(email);
		Optional<User> optionalUser = userDao.findByEmail(email);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();

			List<Event> event1 = eventDao.findAll();
			List<Event> event2 = new ArrayList<Event>();
			for(Event e : event1) {
				if(user.getUserId()!= e.getUser().getUserId()) {
					event2.add(e);
				}
			}
			System.out.println(event2.size());
			List<Event> event3 = user.getRegisteredEvents();
			List<EventResponse> listEventResponse = new ArrayList<>();

			for (Event eee : event2) {
			    if (!event3.contains(eee)) {
			        EventResponse eventResponse = this.modelMapper.map(eee, EventResponse.class);
			        listEventResponse.add(eventResponse);
			    }
			}
			System.out.println(listEventResponse.size());

			ResponseStructure<List<EventResponse>> responseStructure = new ResponseStructure<List<EventResponse>>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Event Data Found");
			responseStructure.setListData(listEventResponse);

			return new ResponseEntity<ResponseStructure<List<EventResponse>>>(responseStructure,HttpStatus.FOUND);
		}
		throw new UserEmailNotFoundException("Email Id Not Found");
	}
	//done

	public ResponseEntity<ResponseStructure<List<EventResponse>>> myEvent(){

		Optional<User> optionalUser = userDao.findByEmail(email);
		
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			List<Event> listEvent = user.getEvent();
			
			List<EventResponse> listeventResponse = new ArrayList<EventResponse>();
			
			for(Event event:listEvent) {
				
					EventResponse eventResponse = this.modelMapper.map(event, EventResponse.class);
					listeventResponse.add(eventResponse);
				}
			
			
			ResponseStructure<List<EventResponse>> responseStructure = new ResponseStructure<List<EventResponse>>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Event Data Found");
			responseStructure.setListData(listeventResponse);

			return new ResponseEntity<ResponseStructure<List<EventResponse>>>(responseStructure,HttpStatus.FOUND);	
		}
		throw new UserEmailNotFoundException("Email Id Not Found");
	}
	//done

	public ResponseEntity<ResponseStructure<EventResponse>> fetchEventData(int id){
		Optional<Event> optionalEvent = eventDao.findById(id);
		if(optionalEvent.isPresent()) {
			Event event = optionalEvent.get();
			EventResponse eventResponse = this.modelMapper.map(event, EventResponse.class);

			ResponseStructure<EventResponse> responseStructure = new ResponseStructure<EventResponse>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Event Data Found");
			responseStructure.setData(eventResponse);

			return new ResponseEntity<ResponseStructure<EventResponse>>(responseStructure,HttpStatus.FOUND);
		}
		throw new EventIdNotFoundException("Event Id Not Found");
	}
	//done

	public ResponseEntity<ResponseStructure<List<EventResponse>>> deleteEvent(int eventId){
		try {
		eventDao.deleteEvent(eventId);
		
		Optional<User> user = userRepository.findByUserName(email);
		
		List<Event> listEvent = user.get().getEvent();

		List<EventResponse> listeventResponse = new ArrayList<EventResponse>();

		for(Event eventcheck :listEvent) {
				EventResponse eventResponse = this.modelMapper.map(eventcheck, EventResponse.class);
				listeventResponse.add(eventResponse);
			}
		

		ResponseStructure<List<EventResponse>> responseStructure = new ResponseStructure<List<EventResponse>>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Event Data Deleted Successfully");
		responseStructure.setListData(listeventResponse);

		return new ResponseEntity<ResponseStructure<List<EventResponse>>>(responseStructure,HttpStatus.OK);}
		catch(Exception exception) {
			throw new UnableToDeleteException("Data Unable to Delete");
		}
	}
	//done
	public void fetchEmail(String email) {
		this.email=email;
	}
}
