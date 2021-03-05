package com.tpn.ticket.controller;


import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tpn.ticket.entity.TicketRequestDto;
import com.tpn.ticket.service.TicketService;
/**
 * 
 * @author Premnath T
 *
 */
//@CrossOrigin(origins = "*" , allowedHeaders = "*")
@RestController
@RequestMapping("/app")
@Validated
public class TicketController {
	
	private static final Logger logger = LogManager.getLogger(TicketController.class);

	@Autowired
	TicketService ticketService;

	/**
	 * createTicket method used to create new Train Ticket for user
	 * 
	 * @param username
	 * @param ticketDto
	 * @return Object
	 */	
	@PostMapping("/ticket")
	@ResponseBody
	public ResponseEntity<Object> createTicket(
			@Valid @NotEmpty(message = "username is manadotry") @RequestParam String username,
			@Valid @RequestBody TicketRequestDto ticketDto) {
		logger.info("createTicket method starts");
		Object result = ticketService.createTicket(username, ticketDto);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Description", "New Train Ticket Booking");
		logger.info("New Train Ticket booked");
		return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(result);
	}

	/**
	 * getHistory method used to retrieve transaction history of user
	 * 
	 * @param username
	 * @return Object
	 */
	@GetMapping("/history")
	@ResponseBody
	public ResponseEntity<Object> getHistory(
			@Valid @NotEmpty(message = "username is manadotry") @RequestParam String username) {
		logger.info("getHistory method starts");
		Object result = ticketService.getHistory(username);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Description", "Users Transactions");
		logger.info("user transactions history retrieved");
		return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(result);

	}
	
	@GetMapping("/info")
	public String getInfo() {
		return "success";
	}

}