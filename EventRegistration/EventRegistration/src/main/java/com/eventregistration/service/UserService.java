package com.eventregistration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.eventregistration.customexception.ExcistingUserException;
import com.eventregistration.customexception.UserEmailNotFoundException;
import com.eventregistration.dao.EventDao;
import com.eventregistration.dao.UserDao;
import com.eventregistration.dto.EventResponse;
import com.eventregistration.dto.UserRequest;
import com.eventregistration.dto.UserResponse;
import com.eventregistration.entity.Event;
import com.eventregistration.entity.User;
import com.eventregistration.util.ResponseStructure;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	EventService eventService;

	@Autowired
	EventDao eventDao;

	@Autowired 
	ModelMapper modelMapper;

	private String email;

	public ResponseEntity<ResponseStructure<UserResponse>> create(UserRequest userRequest){
		List<User> listUser = userDao.findAll();
		if(listUser!=null) 
		{
			for(User user1 : listUser) 
			{
				System.out.println(user1.getUserName());
				if(user1.getUserName().equals(userRequest.getUserName())) {

					throw new ExcistingUserException("Email ID Already Registered");
				}
			}
		}
		User user = new User();
		user.setUserName(userRequest.getUserName());
		user.setPassword(userRequest.getPassword());

		user = userDao.save(user);

		UserResponse userResponse = new UserResponse();
		userResponse.setUserId(user.getUserId());
		userResponse.setUserName(user.getUserName());

		ResponseStructure<UserResponse> responseStructure = new ResponseStructure<UserResponse>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("User Created");
		responseStructure.setData(userResponse);

		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.CREATED);	
	}
	//done

	public ResponseEntity<ResponseStructure<String>> login(String email,String password){

		Optional<User> optionalUser = userDao.findByEmail(email);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			if(user.getPassword().equals(password)) {
				ResponseStructure<String> responseStructure = new ResponseStructure<String>();
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("process success");
				responseStructure.setData("Login successful");
				this.email=email;
				eventService.fetchEmail(email);
				return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.OK);
			}
			else {
				ResponseStructure<String> responseStructure = new ResponseStructure<String>();
				responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
				responseStructure.setMessage("password problem");
				responseStructure.setData("Incorrect Password");
				return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.BAD_REQUEST);
			}
		}
		throw new UserEmailNotFoundException("Data Not Found");
	}
	//done

	public ResponseEntity<ResponseStructure<List<EventResponse>>> registerEvent(int eventId){
		Optional<User> optionalUser = userDao.findByEmail(email);

		Optional<Event> optionalEvent = eventDao.findById(eventId);

		if(optionalUser.isPresent() && optionalEvent.isPresent()) {
			User user = optionalUser.get();
			Event event = optionalEvent.get();

			event.getRegisteredUser().add(user);
			user.getRegisteredEvents().add(event);

			user=userDao.save(user);
			eventDao.save(event);

			List<Event> event1 = eventDao.findAll();
			List<Event> event2 = new ArrayList<Event>();
			for(Event e : event1) {
				if(user.getUserId()!= e.getUser().getUserId()) {
					event2.add(e);
				}
			}
			List<Event> event3 = user.getRegisteredEvents();
			List<EventResponse> listEventResponse = new ArrayList<EventResponse>();
			for (Event eee : event2) {
			    if (!event3.contains(eee)) {
			        EventResponse eventResponse = this.modelMapper.map(eee, EventResponse.class);
			        listEventResponse.add(eventResponse);
			    }
			}


			ResponseStructure<List<EventResponse>> responseStructure = new ResponseStructure<List<EventResponse>>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Event Registered Successfully");
			responseStructure.setListData(listEventResponse);

			return new ResponseEntity<ResponseStructure<List<EventResponse>>>(responseStructure,HttpStatus.OK);
		}

		throw new UserEmailNotFoundException("Email ID or Event ID Not Found");
		
	} 
	//done

	public ResponseEntity<ResponseStructure<List<EventResponse>>> viewRegisterEvent(){

		Optional<User> optionalUser = userDao.findByEmail(email);
		User user = optionalUser.get();
		if(optionalUser.isPresent()) {

			List<Event> eventlist = user.getRegisteredEvents();
			List<EventResponse> listEventResponse = new ArrayList<EventResponse>();

			for(Event list:eventlist) {
				EventResponse eventResponse = this.modelMapper.map(list, EventResponse.class);
				listEventResponse.add(eventResponse);
			}
			ResponseStructure<List<EventResponse>> responseStructure = new ResponseStructure<List<EventResponse>>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Event Data Found");
			responseStructure.setListData(listEventResponse);

			return new ResponseEntity<ResponseStructure<List<EventResponse>>>(responseStructure,HttpStatus.OK);
		}
		throw new UserEmailNotFoundException("Email Id Not Found");
	}
	//done

}
