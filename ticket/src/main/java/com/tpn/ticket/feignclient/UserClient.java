package com.tpn.ticket.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author Premnath T
 *
 */
@FeignClient(name = "http://USER-SERVICE/user/app")
public interface UserClient {

	@GetMapping("/users")
	long findUserId(@RequestParam String username);
}
