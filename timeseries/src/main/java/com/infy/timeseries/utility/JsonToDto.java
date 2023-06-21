package com.infy.timeseries.utility;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.infy.timeseries.dto.TimeSeriesDTO;
import com.infy.timeseries.exception.TimeSeriesException;


@Component
public class JsonToDto{
	public TimeSeriesDTO getPayLoad(Object object) throws TimeSeriesException{
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
    	TimeSeriesDTO dto = mapper.convertValue(object, TimeSeriesDTO.class);
		return dto;
	}
}
