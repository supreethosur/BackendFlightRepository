package com.example.Entity;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class JourneyTransactionDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer journeyTransactionId;
	private Integer journeyId;
	private LocalDate journeyDate;
	private Integer businessSeatsAvailable;
	private Integer nonBusinessSeatsAvailable;
	
	
	
	public Integer getBusinessSeatsAvailable() {
		return businessSeatsAvailable;
	}
	public void setBusinessSeatsAvailable(Integer businessSeatsAvailable) {
		this.businessSeatsAvailable = businessSeatsAvailable;
	}
	public Integer getNonBusinessSeatsAvailable() {
		return nonBusinessSeatsAvailable;
	}
	public void setNonBusinessSeatsAvailable(Integer nonBusinessSeatsAvailable) {
		this.nonBusinessSeatsAvailable = nonBusinessSeatsAvailable;
	}
	public LocalDate getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}
	public Integer getJourneyTransactionId() {
		return journeyTransactionId;
	}
	public void setJourneyTransactionId(Integer journeyTransactionId) {
		this.journeyTransactionId = journeyTransactionId;
	}
	public Integer getJourneyId() {
		return journeyId;
	}
	public void setJourneyId(Integer journeyId) {
		this.journeyId = journeyId;
	}
	
	
	
}
