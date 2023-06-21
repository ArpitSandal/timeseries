package com.infy.timeseries.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.infy.timeseries.dto.PersonDTO;
import com.infy.timeseries.dto.TimeSeriesDTO;
import com.infy.timeseries.entity.TimeSeriesEntity;
import com.infy.timeseries.exception.TimeSeriesException;
import com.infy.timeseries.repository.TimeserieRepository;
import com.infy.timeseries.utility.JsonToDto;

@Service
@Transactional
public class TimeSeriesServiceImpl implements TimeSeriesService {

	@Autowired
	JsonToDto jsonToDto;

	@Autowired
	TimeserieRepository repo;

	@Override
	public String entry(Object object) throws TimeSeriesException {
		TimeSeriesDTO dto = jsonToDto.getPayLoad(object);
		if (dto.getEventType().equalsIgnoreCase("create") && !repo.findByPersonId(dto.getPersonId()).isEmpty()) {
			throw new TimeSeriesException("Cannot create person...ALREADY EXISTS");
		}
		if ((dto.getEventType().equalsIgnoreCase("update") || dto.getEventType().equalsIgnoreCase("patch"))
				&& repo.findByPersonId(dto.getPersonId()).isEmpty()) {
			throw new TimeSeriesException("No such Person Id Found!");
		}
		Gson gson = new Gson();
		String json = gson.toJson(dto.getPersonPayload());
		TimeSeriesEntity entity = new TimeSeriesEntity(dto.getEventId(), dto.getPersonId(), json, dto.getEventType(),
				dto.getCreatedOn(), dto.getRaisedOn(), dto.getSubscribedOn(), dto.getHandledOn(), dto.getProcessedOn());
		return repo.save(entity).toString();
	}

	public List<Object> getAllPerson(Integer personId) throws TimeSeriesException {
		List<String> l = repo.findAllByPersonId(personId);
		return jsonToDto.StringToObject(l);
	}

}
