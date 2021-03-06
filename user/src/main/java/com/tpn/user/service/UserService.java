package com.tpn.user.service;

import org.springframework.stereotype.Service;

import com.tpn.user.dto.UserDto;
import com.tpn.user.entity.User;

/**
 * 
 * @author Premnath T
 *
 */
@Service
public interface UserService {

	UserDto saveUser(User user);

	UserDto findByUserName(String username);

	long findUserId(String username);

}
