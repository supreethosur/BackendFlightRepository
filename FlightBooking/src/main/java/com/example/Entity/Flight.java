package com.example.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer flightId;
	private String flightName;
	private Integer businessSeats;
	private Integer nonBusinessSeats;
	private String scheduleType;
	private String airline;
	private Integer isActive;

	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
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
//	public String getSource() {
//		return source;
//	}
//	public void setSource(String source) {
//		this.source = source;
//	}
//	public String getDestination() {
//		return destination;
//	}
//	public void setDestination(String destination) {
//		this.destination = destination;
//	}
}
