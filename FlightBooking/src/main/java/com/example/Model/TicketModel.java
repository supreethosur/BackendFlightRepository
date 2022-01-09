package com.example.Model;

import java.time.LocalDateTime;

public class TicketModel {
	
	private String passangeName;
	private Integer age;
	private String gender;
	private String mealsType;
	private boolean isBusinessClass;
	private double amount;
	private LocalDateTime journeyDate;
	private String fromToLocation;
	
	
	public String getFromToLocation() {
		return fromToLocation;
	}
	public void setFromToLocation(String fromToLocation) {
		this.fromToLocation = fromToLocation;
	}
	public LocalDateTime getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(LocalDateTime journeyDate) {
		this.journeyDate = journeyDate;
	}
	public String getPassangeName() {
		return passangeName;
	}
	public void setPassangeName(String passangeName) {
		this.passangeName = passangeName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMealsType() {
		return mealsType;
	}
	public void setMealsType(String mealsType) {
		this.mealsType = mealsType;
	}
	public boolean isBusinessClass() {
		return isBusinessClass;
	}
	public void setBusinessClass(boolean isBusinessClass) {
		this.isBusinessClass = isBusinessClass;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	

}
