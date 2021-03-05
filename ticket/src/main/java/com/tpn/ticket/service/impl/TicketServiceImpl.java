package com.tpn.ticket.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import com.tpn.ticket.controller.TicketController;
import com.tpn.ticket.entity.Passenger;
import com.tpn.ticket.entity.Ticket;
import com.tpn.ticket.entity.TicketRequestDto;
import com.tpn.ticket.entity.TicketStatusDto;
import com.tpn.ticket.entity.TrainTicketDto;
import com.tpn.ticket.exception.CustomException;
import com.tpn.ticket.feignclient.TrainClient;
import com.tpn.ticket.feignclient.UserClient;
import com.tpn.ticket.repositry.TicketRepositry;
import com.tpn.ticket.service.TicketService;


/**
 * 
 * @author Premnath T
 *
 */
@Service
public class TicketServiceImpl implements TicketService {

	private static final Logger logger = LogManager.getLogger(TicketController.class);

	@Autowired
	UserClient userClient;

	@Autowired
	TrainClient trainClient;

	@Autowired
	TicketRepositry ticketRepositry;

	@SuppressWarnings("rawtypes")
	@Autowired
	CircuitBreakerFactory circuitBreakerFactory;

	/**
	 * createTicket method used to create train ticket for user
	 */
	@Override
	public TicketStatusDto createTicket(String username, TicketRequestDto ticketDto) {
		logger.info("createTicket method starts");
		long userId = userClient.findUserId(username);
		Ticket ticket = new Ticket();
		TrainTicketDto trainTicketDto=new TrainTicketDto();
		int seatCount=0;
		BeanUtils.copyProperties(ticketDto, ticket);
		ticketDto.getPassenger().stream().forEach(data -> {
			Passenger passenger = new Passenger();
			BeanUtils.copyProperties(data, passenger);
			ticket.getPassenger().add(passenger);
		});
		ticket.setBookedUserId(userId);		
		trainTicketDto=formSeatsCheckDto(ticketDto);
		seatCount=trainClient.getCurrentSeatsCount(trainTicketDto);
		if(seatCount==0 || seatCount<ticketDto.getNumOfSeats()) {
			throw new CustomException("Seats not available on this train");
		}
		ticket.setTicketStatus("BOOKED");
		TrainTicketDto trainStatusDto = new TrainTicketDto();
		Date deptDate = new Date(ticketDto.getDepartDate().getTime());
		trainStatusDto.setDeptDate(deptDate);
		trainStatusDto.setNoOfSeats(ticketDto.getNumOfSeats());
		trainStatusDto.setTrainNumber(ticketDto.getTrainNumber());
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
		circuitBreaker.run(() -> trainClient.updateTrainSeatStatus(trainStatusDto),
				throwable -> getCommonMsg(throwable));
		Ticket resultTicket=ticketRepositry.save(ticket);
		TicketStatusDto resultTicketRequestDto=new TicketStatusDto();
		BeanUtils.copyProperties(resultTicket, resultTicketRequestDto);
		logger.info("createTicket method ends");
		return resultTicketRequestDto;
	}
	
	/**
	 * formSeatsCheckDto method used to form dto for seats count check
	 * @param ticketDto
	 * @return TrainTicketDto
	 */
	private TrainTicketDto formSeatsCheckDto(TicketRequestDto ticketDto) {
		TrainTicketDto trainTicketDto=new TrainTicketDto();
		int trainNumber=ticketDto.getTrainNumber();
		Date deptDate = new Date(ticketDto.getDepartDate().getTime());
		trainTicketDto.setDeptDate(deptDate);
		trainTicketDto.setTrainNumber(trainNumber);
		return trainTicketDto;
	}

	/**
	 * getCommonMsg method used to form messages
	 * 
	 * @param throwable
	 * @return Object
	 */
	public Object getCommonMsg(Throwable throwable) {
		logger.error("Train service is down...");
		return "Train-service is down, Please try after some time.";
	}

	/**
	 * getHistory method used to retrieve user transactions history
	 */
	@Override
	public List<TicketStatusDto> getHistory(String username) {
		logger.info("getHistory method starts");
		long userId = userClient.findUserId(username);
		List<TicketStatusDto> resultList = new ArrayList<>();
		List<Ticket> ticketList = ticketRepositry.findByBookedUserId(userId);
		ticketList.stream().forEach(data -> {
			TicketStatusDto ticketDto = new TicketStatusDto();
			BeanUtils.copyProperties(data, ticketDto);
			resultList.add(ticketDto);
		});
		logger.info("getHistory method ends");
		return resultList;
	}

}
