package com.eval.coronakit.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Embeddable
public class UserAddress {
	
	@NotNull(message="Address cannot be null value")
	@NotBlank(message="Address cannot be blank value")
	
	private String Address;	
	
	@NotNull(message="City cannot be null value")
	@NotBlank(message="City cannot be blank value")
	private String City;
	
	@NotNull(message="State cannot be null value")
	@NotBlank(message="State cannot be blank value")
	private String State;
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	
	

}
