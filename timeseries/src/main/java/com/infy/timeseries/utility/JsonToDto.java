package com.infy.timeseries.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.infy.timeseries.dto.PersonDTO;
import com.infy.timeseries.dto.TimeSeriesDTO;
import com.infy.timeseries.exception.TimeSeriesException;


@Component
public class JsonToDto{
	static ObjectMapper mapper = new ObjectMapper();
	public TimeSeriesDTO getPayLoad(Object object) throws TimeSeriesException{
		
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
    	TimeSeriesDTO dto = mapper.convertValue(object, TimeSeriesDTO.class);
		return dto;
	}
	
	public List<Object> StringToObject(List<String> list) throws TimeSeriesException{
		try {
			return mapper.readValue(list.toString(), new TypeReference<List<Object>>() {});
		} catch (Exception e) {
			throw new TimeSeriesException(e.getMessage());
		}
	}
	
	public PersonDTO StringtoDTO(String person) throws TimeSeriesException{
		try {
			return mapper.readValue(person, new TypeReference<PersonDTO>(){}); 
			}catch(JsonProcessingException e) {
				throw new TimeSeriesException(e.getMessage());
			}
	}
	
	public <T> T[] concatWithArrayCopy(T[] array1,T[] array2) {
		T[] result = Arrays.copyOf(array1, array1.length+array2.length);
		System.arraycopy(array2, 0, result, array1.length, array2.length);
		return result;
	}
}
