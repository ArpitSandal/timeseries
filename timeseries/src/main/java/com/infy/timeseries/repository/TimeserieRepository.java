package com.infy.timeseries.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.infy.timeseries.entity.TimeSeriesEntity;

public interface TimeserieRepository extends CrudRepository<TimeSeriesEntity, LocalDateTime> {
	public List<TimeSeriesEntity> findByPersonId(Integer personId);
}
