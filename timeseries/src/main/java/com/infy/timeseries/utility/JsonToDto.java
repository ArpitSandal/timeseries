package com.infy.timeseries.utility;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.infy.timeseries.dto.PersonDTO;
import com.infy.timeseries.dto.TimeSeriesDTO;
import com.infy.timeseries.exception.TimeSeriesException;

@Component
public class JsonToDto {
	
	private static final Log LOGGER = LogFactory.getLog(JsonToDto.class);
	
	static ObjectMapper mapper = new ObjectMapper();
	
	public <T> void logInfo(String field, T initialState, T finalState) {
		LOGGER.info(field+ " changed from: "+(initialState==null ? "null" : initialState.toString())+" to: "+(finalState==null ? "null" : finalState.toString()));
	}

	public TimeSeriesDTO getPayLoad(Object object) throws TimeSeriesException {

		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		TimeSeriesDTO dto = mapper.convertValue(object, TimeSeriesDTO.class);
		return dto;
	}

	public List<Object> StringToObject(List<String> list) throws TimeSeriesException {
		try {
			return mapper.readValue(list.toString(), new TypeReference<List<Object>>() {
			});
		} catch (Exception e) {
			throw new TimeSeriesException(e.getMessage());
		}
	}

	public PersonDTO StringtoDTO(String person) throws TimeSeriesException {
		try {
			return mapper.readValue(person, new TypeReference<PersonDTO>() {
			});
		} catch (JsonProcessingException e) {
			throw new TimeSeriesException(e.getMessage());
		}
	}

	public <T> T[] concatWithArrayCopy(T[] array1, T[] array2) {
		if(array1==null)
			return array2;
		Set<T> hashSet = new HashSet<T>();
		for (T i : array2) {
			hashSet.add(i);
		}
		for (T i : array1) {
			hashSet.add(i);
		}
		T[] result = Arrays.copyOf(array1, hashSet.size());
		int j = 0;
		for (T i : hashSet) {
			result[j++] = i;
		}
		return result;
	}
}
