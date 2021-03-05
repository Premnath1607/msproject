package com.tpn.zuulgateway.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpn.zuulgateway.config.JwtTokenUtil;
import com.tpn.zuulgateway.model.ApiResponse;
import com.tpn.zuulgateway.model.AuthToken;
import com.tpn.zuulgateway.model.LoginUser;
import com.tpn.zuulgateway.model.UserDto;
import com.tpn.zuulgateway.service.UserService;

/**
 * 
 * @author Premnath T
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

	private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	/**
	 * registerUser method used to generate JWT token for userID.
	 * 
	 * @param loginUser
	 * @return Response
	 * @throws AuthenticationException
	 */
	@PostMapping("/token")
	public ApiResponse<AuthToken> registerUser(@RequestBody LoginUser loginUser) throws AuthenticationException {
		logger.info("register user method starts");
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		final UserDto user = userService.findUser(loginUser.getUsername());
		final String token = jwtTokenUtil.generateToken(user);
		logger.info("Token genereated for user");
		return new ApiResponse<>(200, "success", new AuthToken(token, user.getUsername()));
	}

}
