package com.example.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookingHeader {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pnrNumber;
	private Integer journeyId;
	private Integer bookingStatus;
	private Integer userId;
	private LocalDateTime bookingDate;
	private LocalDate journeyDate;
	private double finalAmount;
	private boolean isOneWayTrip;
	
	
	
	public boolean isOneWayTrip() {
		return isOneWayTrip;
	}
	public void setOneWayTrip(boolean isOneWayTrip) {
		this.isOneWayTrip = isOneWayTrip;
	}
	public double getFinalAmount() {
		return finalAmount;
	}
	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}
	public LocalDate getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}
	public LocalDateTime getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	public Integer getJourneyId() {
		return journeyId;
	}
	public void setJourneyId(Integer journeyId) {
		this.journeyId = journeyId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPnrNumber() {
		return pnrNumber;
	}
	public void setPnrNumber(Integer pnrNumber) {
		this.pnrNumber = pnrNumber;
	}
	public Integer getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(Integer bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	@Override
	public String toString() {
		return "BookingHeader [pnrNumber=" + pnrNumber + ", journeyId=" + journeyId + ", bookingStatus=" + bookingStatus
				+ ", userId=" + userId + ", bookingDate=" + bookingDate + ", journeyDate=" + journeyDate
				+ ", finalAmount=" + finalAmount + ", isOneWayTrip=" + isOneWayTrip + ", isOneWayTrip()="
				+ isOneWayTrip() + ", getFinalAmount()=" + getFinalAmount() + ", getJourneyDate()=" + getJourneyDate()
				+ ", getBookingDate()=" + getBookingDate() + ", getJourneyId()=" + getJourneyId() + ", getUserId()="
				+ getUserId() + ", getPnrNumber()=" + getPnrNumber() + ", getBookingStatus()=" + getBookingStatus()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
	
}
