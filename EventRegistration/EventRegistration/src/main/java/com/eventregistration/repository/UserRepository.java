package com.eventregistration.repository;

import java.util.Optional;

import com.eventregistration.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	public Optional<User> findByUserName(String userName);
}
