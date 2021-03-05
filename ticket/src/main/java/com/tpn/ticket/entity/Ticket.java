package com.tpn.ticket.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Premnath T
 *
 */
@Entity
@Table(name = "TICKET")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TICKET_ID")
	private long ticketId;

	@Column(name = "TRAIN_NUMBER", nullable = false)
	private int trainNumber;

	@Column(name = "DEPART_DATE", nullable = false)
	private Date departDate;

	@Column(name = "DEPART_STATION", nullable = false)
	private String departStation;

	@Column(name = "ARRIVAL_STATION", nullable = false)
	private String arrivalStation;

	@Column(name = "NUM_OF_SEATS", nullable = false)
	private int numOfSeats;

	@Column(name = "PRICE", nullable = false)
	private double price;

	@Column(name = "BOOKED_USER_ID", nullable = false)
	private long bookedUserId;

	@Column(name = "TICKET_STATUS", nullable = false)
	private String ticketStatus;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "pass_ticket_id")
	private List<Passenger> passenger = new ArrayList<>();

	public Ticket() {
		super();
	}

	public long getTicketId() {
		return ticketId;
	}

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

	public List<Passenger> getPassenger() {
		return passenger;
	}

	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public long getBookedUserId() {
		return bookedUserId;
	}

	public void setBookedUserId(long bookedUserId) {
		this.bookedUserId = bookedUserId;
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