package com.tpn.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tpn.ticket.controller.TicketController;

@SpringBootTest
class StatusTest {

	@Autowired
	TicketController ticketController;

	@Test
	void contextLoads() {
		assertThat(ticketController).isNotNull();
	}
}
