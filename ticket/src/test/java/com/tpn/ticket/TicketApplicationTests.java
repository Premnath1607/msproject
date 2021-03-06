package com.tpn.ticket;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.tpn.ticket.entity.Ticket;
import com.tpn.ticket.entity.TicketStatusDto;
import com.tpn.ticket.feignclient.UserClient;
import com.tpn.ticket.repositry.TicketRepositry;
import com.tpn.ticket.service.impl.TicketServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class TicketApplicationTests {

	@InjectMocks
	TicketServiceImpl ticketService;

	@Mock
	TicketRepositry ticketRepositry;

	@Mock
	UserClient userClient;

	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Test should pass when Ticket history is found for user ")
	void getTicketHistoryTest() {
		Ticket ticketStatusDto = new Ticket();
		ticketStatusDto.setTrainNumber(101206);
		ticketStatusDto.setArrivalStation("MAS");
		ticketStatusDto.setDepartStation("CBE");
		ticketStatusDto.setDepartDate(Date.valueOf("2021-05-03"));
		ticketStatusDto.setNumOfSeats(1);
		ticketStatusDto.setPrice(400);
		ticketStatusDto.setTicketStatus("BOOKED");
		List<Ticket> ticketList = new ArrayList<>();
		ticketList.add(ticketStatusDto);
		when(ticketRepositry.findByBookedUserId(13)).thenReturn(ticketList);
		when(userClient.findUserId("jesu14")).thenReturn(Long.valueOf(13));
		// test
		List<TicketStatusDto> ticketResultList = ticketService.getHistory("jesu14");
		assertEquals(1, ticketResultList.size());
	}

}
