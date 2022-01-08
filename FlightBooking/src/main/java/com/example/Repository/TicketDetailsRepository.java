package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.TicketDetails;

public interface TicketDetailsRepository extends JpaRepository<TicketDetails, Integer>{

	List<TicketDetails> findByPnrNoAndClassName(Integer pnrNumber, String string);

	
	
}
