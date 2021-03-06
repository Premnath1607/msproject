package com.tpn.user;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import com.tpn.user.dto.UserDto;
import com.tpn.user.entity.User;
import com.tpn.user.repositry.UserDao;
import com.tpn.user.service.impl.UserServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class UserApplicationTests {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserDao userDao;

	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Test should pass when user is found by name")
	void findByUserNameTest() {
		UserDto userDto = new UserDto();
		User user = new User();
		userDto.setUsername("prem16");
		userDto.setEmail("deena@gmail.com");
		userDto.setFirstName("Premnath");
		userDto.setLastName("T");
		BeanUtils.copyProperties(userDto, user);
		when(userDao.findByUsername("prem16")).thenReturn(Optional.of(user));

		// test
		UserDto userData = userService.findByUserName("prem16");

		assertEquals("Premnath", userData.getFirstName());
		assertEquals("T", userData.getLastName());
		assertEquals("deena@gmail.com", userData.getEmail());
		assertEquals("prem16", userData.getUsername());
	}

	@Test
	@DisplayName("Test should pass when new user is created")
	void saveUserTest() {
		UserDto userDto = new UserDto();
		User user = new User();
		userDto.setUsername("prem18");
		userDto.setEmail("deena16@gmail.com");
		userDto.setFirstName("Premnath");
		userDto.setLastName("T");
		userDto.setPassword("premnath123");
		BeanUtils.copyProperties(userDto, user);

		userService.saveUser(user);

		verify(userDao, times(1)).save(user);
	}

}
