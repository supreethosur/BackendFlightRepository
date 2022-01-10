package com.example.Model;

import java.time.LocalDateTime;

public class NotificationModel {

	private String message;
	private String subject;
	private Integer userId;
	private LocalDateTime timeOfEvent;
	
	public NotificationModel() {
		super();
	}
	
	public NotificationModel(String message, String subject, Integer userId, LocalDateTime timeOfEvent) {
		super();
		this.message = message;
		this.subject = subject;
		this.userId = userId;
		this.timeOfEvent = timeOfEvent;
	}




	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public LocalDateTime getTimeOfEvent() {
		return timeOfEvent;
	}

	public void setTimeOfEvent(LocalDateTime timeOfEvent) {
		this.timeOfEvent = timeOfEvent;
	}

}
