package com.example.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TicketDetails {
	
	@Id
	private Integer ticketNo;
	private Integer passangerId;
	private Integer pnrNo;
	
	
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
