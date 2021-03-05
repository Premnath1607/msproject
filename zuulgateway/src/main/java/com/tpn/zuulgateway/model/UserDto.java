package com.tpn.zuulgateway.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 
 * @author Premnath T
 *
 */
public class UserDto {

	@NotEmpty(message = "First Name is mandatory.")
	@Size(max = 50, message = "FirstName length must be less than 50")
	@Pattern(regexp = "[A-Za-z]*", message = "FirstName is not valid name")
	private String firstName;

	@NotEmpty(message = "Last Name is mandatory.")
	@Size(max = 50, message = "LastName length must be less than 50")
	@Pattern(regexp = "[A-Za-z]*", message = "LastName is not valid name")
	private String lastName;

	@NotEmpty(message = "User Name is mandatory.")
	@Size(max = 50, message = "Username length must be less than 50")
	private String username;

	@NotEmpty(message = "Email is mandatory.")
	@Email
	private String email;

	@NotEmpty(message = "Password is mandatory.")
	@Size(min = 8, max = 16, message = "Password should be min 8 chars and max 16 chars")
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
