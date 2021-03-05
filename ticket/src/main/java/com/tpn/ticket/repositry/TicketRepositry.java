package com.tpn.ticket.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpn.ticket.entity.Ticket;

/**
 * 
 * @author Premnath T
 *
 */
@Repository
public interface TicketRepositry extends JpaRepository<Ticket, Long> {

	List<Ticket> findByBookedUserId(long userId);

}
