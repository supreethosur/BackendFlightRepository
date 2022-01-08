package com.example.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Entity.Flight;
import com.example.util.QueryConstants;

@Repository
public interface FlightsRepository extends JpaRepository<Flight, Integer>{


	@Transactional
	void deleteByFlightId( Integer id );


	@Query(value=QueryConstants.GET_FLIGHTS)
	List<Flight> findFlights(String fromPlace, String toPlace, String scheduleType);


	Flight findByFlightId(Integer flightId);

}
