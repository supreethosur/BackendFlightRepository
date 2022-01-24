package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

	UserDetails findByUserMailId(String emailId);

	UserDetails findByUserMailIdAndUserName(String string, String string2);
	
	

}
