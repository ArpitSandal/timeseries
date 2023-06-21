package com.infy.timeseries.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		TimeSeriesEntity entity = new TimeSeriesEntity(dto.getEventId(), dto.getPersonId(),
				dto.getPersonPayload().toString(), dto.getEventType(), dto.getCreatedOn(), dto.getRaisedOn(),
				dto.getSubscribedOn(), dto.getHandledOn(), dto.getProcessedOn());
		return repo.save(entity).toString();
	}

}
