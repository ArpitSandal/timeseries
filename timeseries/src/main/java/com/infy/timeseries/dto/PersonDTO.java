package com.infy.timeseries.dto;

import java.util.Arrays;

public class PersonDTO {
	Integer personId;
	String firstName;
	String lastName;
	String phone[];
	String email[];
	AddressDTO address[];

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String[] getPhone() {
		return phone;
	}

	public void setPhone(String[] phone) {
		this.phone = phone;
	}

	public String[] getEmail() {
		return email;
	}

	public void setEmail(String[] email) {
		this.email = email;
	}

	public AddressDTO[] getAddress() {
		return address;
	}

	public void setAddress(AddressDTO[] address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "PersonDTO [personId=" + personId + ", firstName=" + firstName + ", lastName=" + lastName + ", phone="
				+ Arrays.toString(phone) + ", email=" + Arrays.toString(email) + ", address=" + Arrays.toString(address)
				+ "]";
	}

}
