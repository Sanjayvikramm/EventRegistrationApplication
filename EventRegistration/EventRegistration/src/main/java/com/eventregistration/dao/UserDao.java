package com.eventregistration.dao;

import java.util.List;
import java.util.Optional;

import com.eventregistration.entity.User;
import com.eventregistration.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class UserDao {
	@Autowired
	UserRepository userRepository;
	public User save(User user) {
		return userRepository.save(user);
	}

	public List<User> findAll(){
		return userRepository.findAll();
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByUserName(email);
	}
}