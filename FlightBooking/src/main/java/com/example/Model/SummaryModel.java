package com.example.Model;

import java.util.List;

public class SummaryModel {

	private List<PassangerModel> passangerList;
	
	private FinalAmount amount;

	public List<PassangerModel> getPassangerList() {
		return passangerList;
	}

	public void setPassangerList(List<PassangerModel> passangerList) {
		this.passangerList = passangerList;
	}

	public FinalAmount getAmount() {
		return amount;
	}

	public void setAmount(FinalAmount amount) {
		this.amount = amount;
	}
	
	
	
	
}
