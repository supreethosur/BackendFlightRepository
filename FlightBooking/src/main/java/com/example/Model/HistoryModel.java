package com.example.Model;

import java.time.LocalDateTime;
import java.util.List;

public class HistoryModel {

	private Integer pnrNo;
//	private List<TicketModel> tickets;
	private String Status;
	public Integer getPnrNo() {
		return pnrNo;
	}
	public void setPnrNo(Integer pnrNo) {
		this.pnrNo = pnrNo;
	}
	
	private Integer TicketNo;
	private String passangeName;
	private Integer age;
	private String gender;
	private String mealsType;
	private String isBusinessClass;
	private double amount;
	private LocalDateTime journeyDate;
	private String fromToLocation;
	
	
	public Integer getTicketNo() {
		return TicketNo;
	}
	public void setTicketNo(Integer ticketNo) {
		TicketNo = ticketNo;
	}
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

	public String getIsBusinessClass() {
		return isBusinessClass;
	}
	public void setIsBusinessClass(String isBusinessClass) {
		this.isBusinessClass = isBusinessClass;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
//	public List<TicketModel> getTickets() {
//		return tickets;
//	}
//	public void setTickets(List<TicketModel> tickets) {
//		this.tickets = tickets;
//	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	
}
