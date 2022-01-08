package com.example.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TicketDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ticketNo;
	private Integer passangerId;
	private Integer pnrNo;
	private String optedMeals;
	private Double amount;
	private String className;
	
	
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getOptedMeals() {
		return optedMeals;
	}
	public void setOptedMeals(String optedMeals) {
		this.optedMeals = optedMeals;
	}
	public Integer getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(Integer ticketNo) {
		this.ticketNo = ticketNo;
	}
	public Integer getPassangerId() {
		return passangerId;
	}
	public void setPassangerId(Integer passangerId) {
		this.passangerId = passangerId;
	}
	public Integer getPnrNo() {
		return pnrNo;
	}
	public void setPnrNo(Integer pnrNo) {
		this.pnrNo = pnrNo;
	}
	
}
