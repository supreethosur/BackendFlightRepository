package com.example.Model;

public class PassangerModel {

	private Integer userId;
	private String userName;
	private String passangeName;
	private Integer age;
	private String gender;
	private String mealsType;
	private Integer pnrNumber;
	private boolean isBusinessClass;
	private double amount;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public boolean isBusinessClass() {
		return isBusinessClass;
	}
	public void setBusinessClass(boolean isBusinessClass) {
		this.isBusinessClass = isBusinessClass;
	}
	public Integer getPnrNumber() {
		return pnrNumber;
	}
	public void setPnrNumber(Integer pnrNumber) {
		this.pnrNumber = pnrNumber;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getMealsType() {
		return mealsType;
	}
	public void setMealsType(String mealsType) {
		this.mealsType = mealsType;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassangeName() {
		return passangeName;
	}
	public void setPassangeName(String passangeName) {
		this.passangeName = passangeName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
}
