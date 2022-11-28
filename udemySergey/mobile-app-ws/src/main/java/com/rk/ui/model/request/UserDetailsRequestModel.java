package com.rk.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailsRequestModel {

	@NotNull(message="First Name can not be empty")
	@Size(min=2, message="first name must not be less than 2 characters")
	private String firstName;
	@NotNull(message="last Name can not be empty")
	@Size(min=2, message="last name must not be less than 2 characters")
	private String lastName;
	@NotNull(message="email can not be empty")
	@Email
	private String email;
	@NotNull(message="password can not be empty")
	@Size(min=8,max=16,
	message="Password must be equal or greater than 8 characters"
			+ " and less than 16 characters")
	private String password;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
