package com.example.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class SearchFlightModel {

	private LocalDate travelStartDate;
	private LocalDate travelReturnDate;
	private String fromPlace;
	private String toPlace;
	private boolean isRoundTrip;
	
	
	public LocalDate getTravelStartDate() {
		return travelStartDate;
	}
	public void setTravelStartDate(LocalDate travelStartDate) {
		this.travelStartDate = travelStartDate;
	}
	
	public LocalDate getTravelReturnDate() {
		return travelReturnDate;
	}
	public void setTravelReturnDate(LocalDate travelReturnDate) {
		this.travelReturnDate = travelReturnDate;
	}
	
	public String getFromPlace() {
		return fromPlace;
	}
	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}
	public String getToPlace() {
		return toPlace;
	}
	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}
	public boolean isRoundTrip() {
		return isRoundTrip;
	}
	public void setRoundTrip(boolean isRoundTrip) {
		this.isRoundTrip = isRoundTrip;
	}
	
	
}
