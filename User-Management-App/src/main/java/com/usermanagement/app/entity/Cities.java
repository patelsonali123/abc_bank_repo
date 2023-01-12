package com.usermanagement.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CITIES_MASTER")
public class Cities {
	@Id
	@GeneratedValue
	public Integer citiesId;
	public String citiesName;
	public Integer stateId;
	public Integer getCitiesId() {
		return citiesId;
	}
	public void setCitiesId(Integer citiesId) {
		this.citiesId = citiesId;
	}
	public String getCitiesName() {
		return citiesName;
	}
	public void setCitiesName(String citiesName) {
		this.citiesName = citiesName;
	}
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	
	

}
