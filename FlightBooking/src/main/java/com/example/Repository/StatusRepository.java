package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer>{

	Status findbyStatusId(Integer bookingStatus);

}
