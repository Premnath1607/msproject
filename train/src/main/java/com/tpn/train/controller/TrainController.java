package com.tpn.train.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpn.train.entity.TrainDto;
import com.tpn.train.entity.TrainStatusRequestDto;
import com.tpn.train.entity.TrainTicketDto;
import com.tpn.train.service.TrainService;

/**
 * 
 * @author Premnath T
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/app")
@Validated
public class TrainController {

	private static final Logger logger = LogManager.getLogger(TrainController.class);

	@Autowired
	TrainService trainService;

	/**
	 * updateTrainSeatStatus method used to update seats detail in Train Data
	 * 
	 * @param trainDto
	 * @return Object
	 */
	@PostMapping("/train")
	public ResponseEntity<String> updateTrainSeatStatus(@Valid @RequestBody TrainTicketDto trainDto) {
		logger.info("updateTrainSeatStatus method starts");
		trainService.updateTrainSeatStatus(trainDto);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Description", "Seats Count Update in Train ");
		logger.info("updateTrainSeatStatus method ends");
		return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).build();
	}

	/**
	 * getTrainStatus method used to retrieve status of trains
	 * 
	 * @param trainStatusRequestDto
	 * @return Object
	 */
	@PostMapping("/status")
	public ResponseEntity<List<TrainDto>> getTrainStatus(
			@Valid @RequestBody TrainStatusRequestDto trainStatusRequestDto) {
		logger.info("getTrainStatus method starts");
		return ResponseEntity.ok(trainService.getTrainStatus(trainStatusRequestDto));
	}

	/**
	 * getCurrentSeatsCount method used to retrieve current status of seats
	 * 
	 * @param trainStatusRequestDto
	 * @return Object
	 */
	@PostMapping("/seats")
	public int getCurrentSeatsCount(@Valid @RequestBody TrainTicketDto trainTicketDto) {
		logger.info("getTrainStatus method starts");
		return trainService.getCurrentSeatsCount(trainTicketDto);
	}
}
