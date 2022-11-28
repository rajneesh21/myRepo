package com.rk.ui.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserDetailsRequestModel {
	@NotNull(message = "First Name can not be empty")
	@Size(min = 2, message = "first name must not be less than 2 characters")
	private String firstName;
	@NotNull(message = "last Name can not be empty")
	@Size(min = 2, message = "last name must not be less than 2 characters")
	private String lastName;

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
