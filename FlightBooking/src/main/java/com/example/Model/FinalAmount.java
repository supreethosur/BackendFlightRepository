package com.example.Model;

public class FinalAmount {

	private double totalTicketAmount;
	private Double taxPercentage;
	private double taxAmount;
	private double serviceCharges;
	private double finalAmount;
	
	
	public double getTotalTicketAmount() {
		return totalTicketAmount;
	}
	public void setTotalTicketAmount(double totalTicketAmount) {
		this.totalTicketAmount = totalTicketAmount;
	}

	public Double getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(Double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	public double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public double getServiceCharges() {
		return serviceCharges;
	}
	public void setServiceCharges(double serviceCharges) {
		this.serviceCharges = serviceCharges;
	}
	public double getFinalAmount() {
		return finalAmount;
	}
	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}
	
}
