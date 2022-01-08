package com.example.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class City {

	@Id
	private Integer cityId;
	private String cityName;
	private Integer StateName;
	private Integer countryName;
	
	
	
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getStateName() {
		return StateName;
	}
	public void setStateName(Integer stateName) {
		StateName = stateName;
	}
	public Integer getCountryName() {
		return countryName;
	}
	public void setCountryName(Integer countryName) {
		this.countryName = countryName;
	}
	
	
	
	
	
	
}
