package com.infy.timeseries.service;

import java.util.List;

import com.infy.timeseries.dto.PersonDTO;
import com.infy.timeseries.exception.TimeSeriesException;

public interface TimeSeriesService {
	String entry(Object object) throws TimeSeriesException;
	List<Object> getAllPerson(Integer personId) throws TimeSeriesException;
}
