package com.infy.timeseries.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.infy.timeseries.entity.TimeSeriesEntity;

public interface TimeserieRepository extends CrudRepository<TimeSeriesEntity, LocalDateTime> {
	public List<TimeSeriesEntity> findByPersonId(Integer personId);

	@Query(value = "Select person_payload from timeseries where person_id=?1", nativeQuery = true)
	public List<String> findAllByPersonId(Integer personId);

	@Query(value = "Select * from timeseries where person_id = ?1 and processed_on<=?2", nativeQuery = true)
	public List<TimeSeriesEntity> findPersonQuery(Integer personId, LocalDateTime time);
}
