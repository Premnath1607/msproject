package com.tpn.train.repositry;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpn.train.entity.Train;

/**
 * 
 * @author Premnath T
 *
 */
@Repository
public interface TrainRepositry extends JpaRepository<Train, Long> {

	List<Train> findBySourceStationContainsAndTargetStationContainsAndDeptDate(String sourceStation,
			String targetStation, Date deptDate);

	Train findByTrainNumberAndDeptDate(int trainNumber, Date deptDate);

}
