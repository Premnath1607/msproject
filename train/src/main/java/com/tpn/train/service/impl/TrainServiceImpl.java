package com.tpn.train.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpn.train.entity.Train;
import com.tpn.train.entity.TrainDto;
import com.tpn.train.entity.TrainStatusRequestDto;
import com.tpn.train.entity.TrainTicketDto;
import com.tpn.train.repositry.TrainRepositry;
import com.tpn.train.service.TrainService;

/**
 * 
 * @author Premnath T
 *
 */
@Service
public class TrainServiceImpl implements TrainService {

	private static final Logger logger = LogManager.getLogger(TrainServiceImpl.class);

	@Autowired
	TrainRepositry trainRepositry;

	/**
	 * updateTrainSeatStatus method used to update seats count in train
	 */
	@Override
	public TrainDto updateTrainSeatStatus(@Valid TrainTicketDto trainDto) {
		logger.info("updateTrainSeatStatus method starts");
		TrainDto trainResultDto = new TrainDto();
		Date deptDate = trainDto.getDeptDate();
		int trainNumber = trainDto.getTrainNumber();
		Train train = trainRepositry.findByTrainNumberAndDeptDate(trainNumber, deptDate);
		train.setPresentSeats(train.getPresentSeats() - trainDto.getNoOfSeats());
		BeanUtils.copyProperties(trainRepositry.save(train), trainDto);
		logger.info("updateTrainSeatStatus method ends");
		return trainResultDto;
	}

	/**
	 * getTrainStatus method used to retrieve train status based on
	 * date,source,destination
	 */
	@Override
	public List<TrainDto> getTrainStatus(TrainStatusRequestDto trainStatusRequestDto) {
		logger.info("getTrainStatus method starts");
		List<TrainDto> trainDtoList = new ArrayList<>();
		Date deptDate = new Date(trainStatusRequestDto.getDeptDate().getTime());
		String sourceStation = trainStatusRequestDto.getSourceStation();
		String targetStation = trainStatusRequestDto.getTargetStation();
		List<Train> trainList = trainRepositry
				.findBySourceStationContainsAndTargetStationContainsAndDeptDate(sourceStation, targetStation, deptDate);
		trainList.stream().forEach(data -> {
			TrainDto trainDto = new TrainDto();
			BeanUtils.copyProperties(data, trainDto);
			trainDtoList.add(trainDto);
		});
		logger.info("getTrainStatus method ends");
		return trainDtoList;
	}

	/**
	 * getCurrentSeatsCount method used to retrieve current seat count in train
	 */
	@Override
	public int getCurrentSeatsCount(@Valid TrainTicketDto trainTicketDto) {
		logger.info("getCurrentSeatsCount method starts");
		Date deptDate = trainTicketDto.getDeptDate();
		int trainNumber = trainTicketDto.getTrainNumber();
		Train train = trainRepositry.findByTrainNumberAndDeptDate(trainNumber, deptDate);
		logger.info("getCurrentSeatsCount method ends");
		return train.getPresentSeats();
	}

}
