package com.tpn.train.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.tpn.train.entity.TrainDto;
import com.tpn.train.entity.TrainStatusRequestDto;
import com.tpn.train.entity.TrainTicketDto;

/**
 * 
 * @author Premnath T
 *
 */
@Service
public interface TrainService {

	TrainDto updateTrainSeatStatus(TrainTicketDto trainDto);

	List<TrainDto> getTrainStatus(TrainStatusRequestDto trainStatusRequestDto);

	int getCurrentSeatsCount(@Valid TrainTicketDto trainTicketDto);

}
