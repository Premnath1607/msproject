package com.tpn.zuulgateway.feignclient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tpn.zuulgateway.model.UserDto;

/**
 * 
 * @author Premnath T
 *
 */
@FeignClient(name="http://USER-SERVICE/user/app")
public interface UserClient {

	@PostMapping("/name")
	UserDto findByUserName(@RequestParam String username);

	@PostMapping("/user")
	UserDto saveUser(@RequestBody  UserDto user);
}
