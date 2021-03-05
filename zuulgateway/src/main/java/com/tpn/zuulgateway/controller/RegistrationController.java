package com.tpn.zuulgateway.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tpn.zuulgateway.model.UserDto;
import com.tpn.zuulgateway.service.UserService;

/**
 * 
 * @author Premnath T
 *
 */
@CrossOrigin(origins = "*")
@RestController
@Validated
@ResponseBody
public class RegistrationController {
	
	private static final Logger logger = LogManager.getLogger(RegistrationController.class);

	@Autowired
	private UserService userService;

	/**
	 * signupUser method used to register the user data
	 * @param userDto
	 * @return Response
	 */
	@PostMapping("/signup")
	@ResponseBody
	public ResponseEntity<UserDto> signupUser(@Valid @RequestBody UserDto userDto) {
		logger.info("signUpUser method starts");
		return ResponseEntity.ok(userService.saveUser(userDto));
	}

}
