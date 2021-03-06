package com.tpn.train;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

import com.tpn.train.entity.TrainStatusRequestDto;
import com.tpn.train.entity.Train;
import com.tpn.train.entity.TrainDto;
import com.tpn.train.repositry.TrainRepositry;
import com.tpn.train.service.impl.TrainServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class TrainApplicationTests {

	@InjectMocks
	TrainServiceImpl trainService;

	@Mock
	TrainRepositry trainRepositry;

	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Test should pass when train search result is found ")
	void findTrainStatusTest() {
		Train train = new Train();
		train.setSourceStation("CBE");
		train.setTargetStation("MAS");
		train.setDeptDate(Date.valueOf("2021-05-03"));
		train.setPresentSeats(90);
		train.setTotalSeats(100);
		train.setTrainNumber(101207);
		List<Train> trainDataList=new ArrayList<>();
		trainDataList.add(train);
		TrainStatusRequestDto trainRequestDto =new TrainStatusRequestDto();
		BeanUtils.copyProperties(train, trainRequestDto);
		when(trainRepositry.findBySourceStationContainsAndTargetStationContainsAndDeptDate("CBE", "MAS",
				Date.valueOf("2021-05-03"))).thenReturn(trainDataList);
		
		// test
		List<TrainDto> trainList = trainService.getTrainStatus(trainRequestDto);

		assertEquals(1, trainList.size());		
	}

}