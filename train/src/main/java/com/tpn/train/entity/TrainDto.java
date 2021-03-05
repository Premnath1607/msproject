package com.tpn.train.entity;

import java.sql.Date;

/**
 * 
 * @author Premnath T
 *
 */
public class TrainDto {

	private String sourceStation;

	private String targetStation;

	private Date deptDate;

	private int trainNumber;

	private int totalSeats;

	private int presentSeats;

	public String getSourceStation() {
		return sourceStation;
	}

	public void setSourceStation(String sourceStation) {
		this.sourceStation = sourceStation;
	}

	public String getTargetStation() {
		return targetStation;
	}

	public void setTargetStation(String targetStation) {
		this.targetStation = targetStation;
	}

	public Date getDeptDate() {
		return deptDate;
	}

	public void setDeptDate(Date deptDate) {
		this.deptDate = deptDate;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
	}

	public int getPresentSeats() {
		return presentSeats;
	}

	public void setPresentSeats(int presentSeats) {
		this.presentSeats = presentSeats;
	}

}
