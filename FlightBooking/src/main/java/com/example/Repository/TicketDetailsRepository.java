package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Entity.TicketDetails;
import com.example.util.QueryConstants;

public interface TicketDetailsRepository extends JpaRepository<TicketDetails, Integer>{

	List<TicketDetails> findByPnrNoAndClassName(Integer pnrNumber, String string);

	List<TicketDetails> findByPnrNo(Integer pnrNumber);

	List<TicketDetails> findByPnrNoAndIsActive(Integer pnrNumber, int i);

	TicketDetails findByTicketNo(Integer ticketId);

	List<TicketDetails> findByPnrNoAndClassNameAndIsActive(Integer pnrNumber, String classname, int i);

	@Query(value=QueryConstants.DELETE_PASSANGER)
	List<TicketDetails> updatePassangerId(Integer passangerId);

	
	
}
