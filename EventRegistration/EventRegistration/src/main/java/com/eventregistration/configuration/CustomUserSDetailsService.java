/*package com.eventregistration.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.eventregistration.customexception.UserEmailNotFoundException;
import com.eventregistration.entity.User;
import com.eventregistration.repository.UserRepository;

@Service
public class CustomUserSDetailsService implements UserDetailsService{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("i aOptional<T>r service class");
		
		Optional<User> optionalUser = userRepository.findByUserName(userName);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			return new CustomUserDetails(user);
		}
		throw new UserEmailNotFoundException("UserName Not Found");
	}

}*/
