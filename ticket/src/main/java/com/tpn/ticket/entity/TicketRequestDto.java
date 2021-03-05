package com.tpn.ticket.entity;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author Premnath T
 *
 */
public class TicketRequestDto {

	@NotNull(message = "Number of Seat is mandatory.")
	@Min(value = 1, message = "Seats should not empty")
	@Max(value = 4, message = "Seats should not more than 4")
	@Digits(integer = 1, fraction = 0, message = "Enter valid Seats Number")
	private int numOfSeats;

	@NotNull(message = "Ticket price is mandatory.")
	private double price;

	@NotNull(message = "Departure Date is mandatory.")
	@Future(message = "Departue Date must be in future")
	private Date departDate;

	@NotEmpty(message = "Departure Station is mandatory.")
	@Length(min = 3, max = 3, message = "Departure Station is not Valid")
	private String departStation;

	@NotEmpty(message = "Arrival Station is mandatory.")
	@Length(min = 3, max = 3, message = "Arrival Station is not Valid")
	private String arrivalStation;

	@NotNull(message = "Passenger Detail is mandatory.")
	@Size(min = 1, max = 4, message = "Passengers list is must be less than 4")
	private List<@Valid PassengerDto> passenger;

	@NotNull(message = "Train Number  is mandatory.")
	@Digits(integer = 6, fraction = 0, message = "Enter valid Train Number")
	private int trainNumber;

	private String ticketStatus;

	public int getNumOfSeats() {
		return numOfSeats;
	}

	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDepartDate() {
		return departDate;
	}

	public void setDepartDate(Date departDate) {
		this.departDate = departDate;
	}

	public String getDepartStation() {
		return departStation;
	}

	public void setDepartStation(String departStation) {
		this.departStation = departStation;
	}

	public String getArrivalStation() {
		return arrivalStation;
	}

	public void setArrivalStation(String arrivalStation) {
		this.arrivalStation = arrivalStation;
	}

	public List<PassengerDto> getPassenger() {
		return passenger;
	}

	public void setPassenger(List<PassengerDto> passenger) {
		this.passenger = passenger;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public int getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
	}

}
