package com.example.Entity;

import javax.persistence.Id;

public class Status {

	@Id
	private Integer statusId;
	private String statusDescription;
	
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	
	
	
	
	
}
