package com.tpn.zuulgateway.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tpn.zuulgateway.exception.CustomException;
import com.tpn.zuulgateway.feignclient.UserClient;
import com.tpn.zuulgateway.model.UserDto;
import com.tpn.zuulgateway.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 
 * @author Premnath T
 *
 */
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Autowired
	private UserClient userClient;

	/**
	 * loadUserByUsername method used to implement security features
	 */	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("loadUserByName method starts");
		UserDto user = userClient.findByUserName(username);
		if (Optional.ofNullable(user.getUsername()).isEmpty()) {
			logger.info("User Credentials is wrong...");
			throw new UsernameNotFoundException("Invalid username or password.");
		} else {
			logger.info("User Details Formed");
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					getAuthority());
		}
	}

	/**
	 * getAuthority method used to form the list of authorities
	 * 
	 * @return List
	 */
	private List<SimpleGrantedAuthority> getAuthority() {
		logger.info("Authority list created");
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	/**
	 * findUser method used to get the data of particular user.
	 */
	@Override
	public UserDto findUser(String username) {
		logger.info("findUser method starts");
		return userClient.findByUserName(username);
	}

	/**
	 * saveUser method used to create user in Database
	 */
	@Override
	public UserDto saveUser(UserDto user) {
		logger.info("saveUser method starts");
		UserDto newUser = new UserDto();
		UserDto userData = userClient.findByUserName(user.getUsername());
		if (!Optional.ofNullable(userData.getUsername()).isEmpty()) {
			logger.error("UserName conflict");
			throw new CustomException("Username/Email already Exists..Try with another one");
		}
		newUser.setUsername(user.getUsername());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setEmail(user.getEmail());
		logger.info("New user Data Created");
		return userClient.saveUser(newUser);
	}

}
