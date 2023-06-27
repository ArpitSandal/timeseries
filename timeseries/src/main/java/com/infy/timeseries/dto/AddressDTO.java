package com.infy.timeseries.dto;

import java.util.Objects;

public class AddressDTO {
	String addressName;
	String line1;
	String line2;
	String country;
	String pincode;
	String state;

	String city;

	@Override
	public int hashCode() {
		return Objects.hash(addressName.toLowerCase());
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

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

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	@Override
	public String toString() {
		return "AddressDTO [addressName=" + addressName + ", line1=" + line1 + ", line2=" + line2 + ", country="
				+ country + ", pincode=" + pincode + ", state=" + state + ", city=" + city + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		// this is old object and other is new object from api
		AddressDTO other = (AddressDTO) obj;
		if (addressName.equalsIgnoreCase(other.addressName)) {
			if (other.line1 == null)
				other.setLine1(this.line1);
			if (other.line2 == null)
				other.setLine2(this.line2);
			if (other.country == null)
				other.country = this.country;
			if (other.state == null)
				other.state = this.state;
			if (other.city == null)
				other.city = this.city;
			if (other.pincode == null)
				other.pincode = this.pincode;
			return true;
		}
		return false;

	}

}
