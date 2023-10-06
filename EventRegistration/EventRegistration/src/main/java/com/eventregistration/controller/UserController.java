package com.eventregistration.controller;

import com.eventregistration.dto.EventResponse;
import com.eventregistration.dto.UserRequest;
import com.eventregistration.dto.UserResponse;
import com.eventregistration.service.UserService;
import com.eventregistration.util.ResponseStructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserService userService;
	@GetMapping("/login")
	public String login() {
		return "hello";
	}

	@PostMapping("/create")

	public ResponseEntity<ResponseStructure<UserResponse>> create(@RequestBody UserRequest userRequest){
		return userService.create(userRequest);
	}


	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<String>> login(@RequestBody UserRequest userRequest){

		String email = userRequest.getUserName();
		String password = userRequest.getPassword();
		return userService.login(email, password);
	}


	@PostMapping("/registerEvent")
	public ResponseEntity<ResponseStructure<List<EventResponse>>> registerEvent(@RequestParam int eventId){
		return userService.registerEvent(eventId);
	}


	@GetMapping("/viewRegisterEvent")
	public ResponseEntity<ResponseStructure<List<EventResponse>>> viewRegisterEvent(){
		return userService.viewRegisterEvent();

	}
}
