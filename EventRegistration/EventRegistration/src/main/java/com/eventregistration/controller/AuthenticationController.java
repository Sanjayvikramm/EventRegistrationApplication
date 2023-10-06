//package com.eventregistration.controller;

/*import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventregistration.configuration.CustomUserDetails;
import com.eventregistration.customexception.ExcistingUserException;
import com.eventregistration.dao.UserDao;
import com.eventregistration.dto.UserRequest;
import com.eventregistration.entity.User;
import com.eventregistration.service.AuthenticationService;
import com.eventregistration.util.ResponseStructure;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {
	
	@Autowired
	AuthenticationService authService;
	
	@PostMapping("/register")
	public CustomUserDetails register(@RequestBody UserRequest userRequest) {
		return authService.create(userRequest.getUserName(), userRequest.getPassword());
	}
	
	@PostMapping("/login")
	public CustomUserDetails login(@RequestBody UserRequest userRequest) {
		return null;
	}

}*/
