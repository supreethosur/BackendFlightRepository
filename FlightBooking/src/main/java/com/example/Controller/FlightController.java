package com.example.Controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Flight;
import com.example.Model.FlightModel;
import com.example.Model.PassangerModel;
import com.example.Service.FlightService;

@RestController
public class FlightController {
	
@Autowired
FlightService flightService;

	@GetMapping("/{Id}")
	public  Flight  getFlightsById(@PathVariable Integer Id) throws Exception {
		System.out.println("inside Controller");
		Flight flights = flightService.findById(Id);	 
		return flights;
	}
	
	@PostMapping("/addFlights")
	public FlightModel addFlights(@RequestBody FlightModel ipflight) throws Exception {
		
		return flightService.addFlight(ipflight);
	}
	
	
	@DeleteMapping("/deleteFlight/{Id}")
	public void deleteFlight(@PathVariable Integer Id) throws Exception {
		flightService.deleteById(Id);
	}
	
	@PutMapping("/updateFlight")
	public FlightModel updateFlight(@RequestBody FlightModel flight) throws Exception {
		FlightModel flight1=flightService.updateFlight(flight);
		return flight1;
	}
	

	@GetMapping("/getCityStartsWith/{city}")
	public List<String> getCityStartsWith(@PathVariable String cityName) {
		List<String> cities = flightService.getCities(cityName);
		return cities;
	}
	
	@PostMapping("/addPassanger}")
	public void addPassanger(@RequestBody PassangerModel passangerModel) {
		
		flightService.addPassanger(passangerModel);
	}
	
	
	@PostMapping("/proceedWithFlight}")
	public void proceedWithBooking(HashMap<String,Integer> model) {
		
		flightService.proceedWithBooking(model);
		
		
		
	}
	
	
	
	
}
