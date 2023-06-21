package com.infy.timeseries.dto;

public class AddressDTO {
	String state;
	String city;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "AddressDTO [state=" + state + ", city=" + city + "]";
	}

}
