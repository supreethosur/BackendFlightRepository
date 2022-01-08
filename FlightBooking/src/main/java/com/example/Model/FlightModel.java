package com.example.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightModel {

	private Integer flightId;
	private Integer journeyId;
	private String flightName; 
	private String airline;
	private LocalDate departureDate;
	private LocalTime departureTime;
	private LocalDate arrivalDate;
	private LocalTime arrivalTime;
	private String fromLocation;
	private String toLocation;
	private Integer businessSeats;
	private Integer nonBusinessSeats;
	private String scheduleType;
	
	
	public LocalDate getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public Integer getJourneyId() {
		return journeyId;
	}
	public void setJourneyId(Integer journeyId) {
		this.journeyId = journeyId;
	}
	public Integer getFlightId() {
		return flightId;
	}
	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	public Integer getBusinessSeats() {
		return businessSeats;
	}
	public void setBusinessSeats(Integer businessSeats) {
		this.businessSeats = businessSeats;
	}
	public Integer getNonBusinessSeats() {
		return nonBusinessSeats;
	}
	public void setNonBusinessSeats(Integer nonBusinessSeats) {
		this.nonBusinessSeats = nonBusinessSeats;
	}
	public String getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
	
	
	
}
