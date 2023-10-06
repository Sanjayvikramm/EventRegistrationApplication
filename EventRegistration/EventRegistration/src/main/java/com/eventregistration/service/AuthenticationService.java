/*package com.eventregistration.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eventregistration.configuration.CustomUserDetails;
import com.eventregistration.customexception.ExcistingUserException;
import com.eventregistration.dao.UserDao;
import com.eventregistration.entity.User;

@Service
@Transactional
public class AuthenticationService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	ModelMapper modelMapper;

	public CustomUserDetails create(String userName, String password){
		List<User> listUser = userDao.findAll();
		if(listUser!=null) 
		{
			for(User user1 : listUser) 
			{
				System.out.println(user1.getUserName());
				if(user1.getUserName().equals(userName)) {

					throw new ExcistingUserException("Email ID Already Registered");
				}
			}
		}
		User user = new User();
		user.setUserName(userName);
		user.setPassword(passwordEncoder.encode(password));

		user=userDao.save(user);

		return new CustomUserDetails(user);
	}
	
	
}*/
