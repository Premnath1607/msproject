package com.tpn.ticket.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Premnath T
 *
 */
@Entity
@Table(name = "passenger")
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PASSENGER_ID")
	private Long passengerId;

	@NotNull
	@Size(max = 50)
	@Column
	private String passengerName;

	@NotNull
	@Column
	private int age;

	public Passenger() {

	}

	public Long getPassengerId() {
		return passengerId;
	}

	public Passenger(@NotNull @Size(max = 50) String passengerName, @NotNull int age) {
		super();
		this.passengerName = passengerName;
		this.age = age;
	}

	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}

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
