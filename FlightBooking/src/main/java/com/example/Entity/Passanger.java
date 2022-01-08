package com.example.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Passanger {
	
	@Id
	private Integer passengerId;
	private String passangerName;
	private Integer age;
	private String gender;
	private Integer associatedUserId;
	
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}
	public String getPassangerName() {
		return passangerName;
	}
	public void setPassangerName(String passangerName) {
		this.passangerName = passangerName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getAssociatedUserId() {
		return associatedUserId;
	}
	public void setAssociatedUserId(Integer associatedUserId) {
		this.associatedUserId = associatedUserId;
	}
	
	

}
