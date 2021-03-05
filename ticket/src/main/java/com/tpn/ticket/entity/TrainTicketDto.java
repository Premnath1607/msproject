package com.tpn.ticket.entity;

import java.sql.Date;

/**
 * 
 * @author Premnath T
 *
 */
public class TrainTicketDto {

	private Date deptDate;

	private int trainNumber;

	private int noOfSeats;

	public Date getDeptDate() {
		return deptDate;
	}

	public void setDeptDate(Date deptDate) {
		this.deptDate = deptDate;
	}

	public int getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

}
