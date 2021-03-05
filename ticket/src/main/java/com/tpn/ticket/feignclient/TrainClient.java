package com.tpn.ticket.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tpn.ticket.entity.TrainTicketDto;

/**
 * 
 * @author Premnath T
 *
 */
@FeignClient(name = "http://TRAIN-SERVICE/train/app")
public interface TrainClient {

	@PostMapping("/train")
	ResponseEntity<String> updateTrainSeatStatus(@RequestBody TrainTicketDto trainTicketDto);

	@PostMapping("/seats")
	int getCurrentSeatsCount(@RequestBody TrainTicketDto trainTicketDto);

}
