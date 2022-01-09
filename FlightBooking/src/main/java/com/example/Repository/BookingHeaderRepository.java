package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.BookingHeader;

public interface BookingHeaderRepository  extends JpaRepository<BookingHeader, Integer>{

	BookingHeader findByPnrNumber(Integer pnrNumber);

	BookingHeader findByPnrNumberAndBookingStatus(Integer pnrNumber, int i);

	List<BookingHeader> findByUserId(Integer userId);
	
	

}
