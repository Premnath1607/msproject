package com.tpn.train.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Premnath T
 *
 */
@Entity
@Table(name = "train")
public class Train {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TRAIN_ID")
	private long trainId;

	@Column(name = "SOURCE_STATION", nullable = false)
	private String sourceStation;

	@Column(name = "TARGET_STATION", nullable = false)
	private String targetStation;

	@Column(name = "DEPT_DATE", nullable = false)
	private Date deptDate;

	@Column(name = "TRAIN_NUMBER", nullable = false, unique = true)
	private int trainNumber;

	@Column(name = "TOTAL_SEATS", nullable = false)
	private int totalSeats;

	@Column(name = "PRESENT_SEATS", nullable = false)
	private int presentSeats;

	public long getTrainId() {
		return trainId;
	}

	public void setTrainId(long trainId) {
		this.trainId = trainId;
	}

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
