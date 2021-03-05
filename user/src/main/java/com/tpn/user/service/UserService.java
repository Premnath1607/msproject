package com.tpn.user.service;

import org.springframework.stereotype.Service;

import com.tpn.user.dto.UserDto;

/**
 * 
 * @author Premnath T
 *
 */
@Service
public interface UserService {

	UserDto saveUser(UserDto user);

	UserDto findByUserName(String username);

	long findUserId(String username);

}
