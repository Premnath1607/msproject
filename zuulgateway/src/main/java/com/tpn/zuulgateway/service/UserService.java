package com.tpn.zuulgateway.service;

import com.tpn.zuulgateway.model.UserDto;

/**
 * 
 * @author Premnath T
 *
 */
public interface UserService {

	UserDto saveUser(UserDto user);

	UserDto findUser(String username);
}
