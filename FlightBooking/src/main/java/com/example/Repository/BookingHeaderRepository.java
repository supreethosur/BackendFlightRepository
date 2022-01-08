package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.BookingHeader;

public interface BookingHeaderRepository  extends JpaRepository<BookingHeader, Integer>{

	BookingHeader findByPnrNumber(Integer pnrNumber);
	
	

}
