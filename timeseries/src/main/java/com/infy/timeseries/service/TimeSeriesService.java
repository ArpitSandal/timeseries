package com.infy.timeseries.service;

import com.infy.timeseries.exception.TimeSeriesException;

public interface TimeSeriesService {
	String entry(Object object) throws TimeSeriesException;
}
