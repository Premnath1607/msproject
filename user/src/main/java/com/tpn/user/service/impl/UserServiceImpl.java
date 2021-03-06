package com.tpn.user.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpn.user.dto.UserDto;
import com.tpn.user.entity.User;
import com.tpn.user.exception.CustomException;
import com.tpn.user.repositry.UserDao;
import com.tpn.user.service.UserService;

/**
 * 
 * @author Premnath T
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	/**
	 * saveUser method used to save new user into database
	 */
	@Override
	public UserDto saveUser(User user) {
		User newUser = new User();
		UserDto resultData = new UserDto();
		try {
			newUser = userDao.save(user);
		} catch (Exception e) {
			throw new CustomException("Username/Email already Exists");
		}
		if (!Optional.ofNullable(newUser).isEmpty()) {
			BeanUtils.copyProperties(newUser, resultData);
		}
		return resultData;
	}

	/**
	 * findByUserName method used to retrieve user records based on name
	 */
	@Override
	public UserDto findByUserName(String username) {
		UserDto userDto = new UserDto();
		Optional<User> user = userDao.findByUsername(username);
		if (user.isPresent()) {
			BeanUtils.copyProperties(user.get(), userDto);
		}
		return userDto;
	}

	/**
	 * findUserId method used to retrieve user id based on name
	 */
	@Override
	public long findUserId(String username) {
		Optional<User> user = userDao.findByUsername(username);
		if (!user.isPresent()) {
			throw new CustomException("Username not present in Database");
		} else {
			return user.get().getId();
		}
	}

}
