package com.example.Repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.JourneyTransactionDetails;

public interface JourneyTransactionDetailsRepository extends JpaRepository<JourneyTransactionDetails, Integer> {

	JourneyTransactionDetails findByJourneyIdAndJourneyDate(Integer journeyId, LocalDate travelStartDate);

	
	
}
