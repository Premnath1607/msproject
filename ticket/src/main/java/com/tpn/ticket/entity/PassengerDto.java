package com.tpn.ticket.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 
 * @author Premnath T
 *
 */
public class PassengerDto {

	@NotEmpty(message = "Passenger name is mandatory.")
	@Size(max = 50, message = "Passenger Name length must be less than 50")
	@Pattern(regexp = "[A-Za-z]*", message = "Passenger name is not valid name")
	private String passengerName;

	@NotNull(message = "Age is mandatory.")
	@Min(value = 1, message = "Age should be valid")
	@Max(value = 100, message = "Age should be valid")
	private int age;


	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
