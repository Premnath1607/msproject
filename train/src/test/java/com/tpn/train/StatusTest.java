package com.tpn.train;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tpn.train.controller.TrainController;

@SpringBootTest
class StatusTest {

	@Autowired
	TrainController trainController;

	@Test
	void contextLoads() {
		assertThat(trainController).isNotNull();
	}
}
