package com.tpn.train.entity;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author Premnath T
 *
 */
public class TrainStatusRequestDto {

	@NotNull(message = "Departure Date is mandatory.")
	@Future(message = "Departue Date must be in future")
	private Date deptDate;

	@NotEmpty(message = "Departure Station is mandatory.")
	@Length(min = 3, max = 3, message = "Departure Station is not Valid")
	private String sourceStation;

	@NotEmpty(message = "Destination Station is mandatory.")
	@Length(min = 3, max = 3, message = "Destination Station is not Valid")
	private String targetStation;

	public Date getDeptDate() {
		return deptDate;
	}

	public void setDeptDate(Date deptDate) {
		this.deptDate = deptDate;
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

}
