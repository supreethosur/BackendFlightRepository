package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Passanger;

public interface PassengerRepository  extends JpaRepository<Passanger, Integer>{

	Passanger findByPassengerId(Integer passangerId);

	
	
	
}
