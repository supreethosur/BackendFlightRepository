package com.example.Model;

import java.util.List;

public class ProceedBookingModel {

	private List<JourneyInputModel> journeyInputModel;
	private Integer userId;
	private boolean isOneWaytrip;
	
	
	
	public boolean isOneWaytrip() {
		return isOneWaytrip;
	}
	public void setOneWaytrip(boolean isOneWaytrip) {
		this.isOneWaytrip = isOneWaytrip;
	}
	
	public List<JourneyInputModel> getJourneyInputModel() {
		return journeyInputModel;
	}
	public void setJourneyInputModel(List<JourneyInputModel> journeyInputModel) {
		this.journeyInputModel = journeyInputModel;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
