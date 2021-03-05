package com.tpn.ticket.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpn.ticket.entity.Passenger;

/**
 * 
 * @author Premnath T
 *
 */
@Repository
public interface PassengerRepositry extends JpaRepository<Passenger, Long> {

}
