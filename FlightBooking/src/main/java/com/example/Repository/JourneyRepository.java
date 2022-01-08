package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Entity.Journey;
import com.example.util.QueryConstants;

public interface JourneyRepository extends JpaRepository<Journey, Integer>{

	Journey findByJourneyId(Integer journeyId);

	@Query(value=QueryConstants.GET_JOURNEY)
	List<Journey> getJourney(Integer flightId, String fromLocation, String toLocation);

}
