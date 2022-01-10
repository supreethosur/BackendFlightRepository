package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Entity.City;
import com.example.util.QueryConstants;

public interface CityRepository extends JpaRepository<City,Integer>{

//	@Query(value=QueryConstants.GET_CITIES)
//	List<String> findCityNameByCityNameStartsWith(String cityName);

	List<City> findByCityNameStartsWith(String cityName);

	
	        
}
