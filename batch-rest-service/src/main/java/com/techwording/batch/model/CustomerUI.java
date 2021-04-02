package com.techwording.batch.model;

public class CustomerUI {

	private String firstName;

	private String lastName;

	public CustomerUI(String firstName2, String lastName2) {

		this.firstName = firstName2;
		this.lastName = lastName2;
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

}
