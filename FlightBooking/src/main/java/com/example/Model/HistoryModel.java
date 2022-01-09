package com.example.Model;

import java.util.List;

public class HistoryModel {

	private Integer pnrNo;
	private List<TicketModel> tickets;
	private String Status;
	public Integer getPnrNo() {
		return pnrNo;
	}
	public void setPnrNo(Integer pnrNo) {
		this.pnrNo = pnrNo;
	}
	public List<TicketModel> getTickets() {
		return tickets;
	}
	public void setTickets(List<TicketModel> tickets) {
		this.tickets = tickets;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	
}
