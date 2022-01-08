package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Journey;

public interface JourneyRepository extends JpaRepository<Journey, Integer>{

	Journey findByJourneyId(Integer journeyId);

}
