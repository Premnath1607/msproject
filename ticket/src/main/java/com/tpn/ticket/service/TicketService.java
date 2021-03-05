package com.tpn.ticket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tpn.ticket.entity.TicketRequestDto;
import com.tpn.ticket.entity.TicketStatusDto;

/**
 * 
 * @author Premnath T
 *
 */
@Service
public interface TicketService {

	TicketStatusDto createTicket(String username, TicketRequestDto ticketDto);

	List<TicketStatusDto> getHistory(String username);

}
