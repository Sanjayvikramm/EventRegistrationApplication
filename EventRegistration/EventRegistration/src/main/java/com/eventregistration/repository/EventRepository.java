package com.eventregistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventregistration.entity.Event;

public interface EventRepository extends JpaRepository<Event,Integer>{
}
