package com.tpn.user.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tpn.user.dto.UserDto;
import com.tpn.user.entity.User;
import com.tpn.user.service.UserService;

/**
 * 
 * @author Premnath T
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/app")
@ResponseBody
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	UserService userService;

	/**
	 * createUser method used to create new user record in database
	 * 
	 * @param user
	 * @return Object
	 */
	@PostMapping("/user")
	public UserDto createUser(@RequestBody UserDto userDto) {
		logger.info("createUser method starts");
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		return userService.saveUser(user);
	}

	/**
	 * findByUserName method used to get the user details based on name
	 * 
	 * @param username
	 * @return Object
	 */
	@GetMapping("/name")
	public UserDto findByUserName(@RequestParam String username) {
		logger.info("findByUserName method starts");
		return userService.findByUserName(username);
	}

	/**
	 * findUserId method used to get the userID based on name
	 * 
	 * @param username
	 * @return Object
	 */
	@GetMapping("/users")
	public long findUserId(@RequestParam String username) {
		logger.info("findUserId method starts");
		return userService.findUserId(username);
	}

}
