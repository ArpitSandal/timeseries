package com.infy.timeseries.service;

import java.time.LocalDateTime;
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
		repo.save(entity);
		return entity.getEventType()+" Person ID " + entity.getPersonId() + " at " + entity.getTimestamp();
	}

	public List<Object> getAllPerson(Integer personId) throws TimeSeriesException {
		List<String> list = repo.findAllByPersonId(personId);
		if (list.isEmpty())
			throw new TimeSeriesException("No such Person Id Found!");
		return jsonToDto.StringToObject(list);
	}

	public Object getPersonQuery(Integer personId, LocalDateTime time) throws TimeSeriesException {
		List<TimeSeriesEntity> list = repo.findPersonQuery(personId, time);
		if (list.isEmpty()) {
			throw new TimeSeriesException("Person Id does not exsist till the given time");
		}

		PersonDTO person = new PersonDTO();

		for (TimeSeriesEntity i : list) {
			if (i.getEventType().equalsIgnoreCase("update") || i.getEventType().equalsIgnoreCase("create")) {
				person = jsonToDto.StringtoDTO(i.getPersonPayload());
			} else {
				PersonDTO person1 = new PersonDTO();
				person1 = jsonToDto.StringtoDTO(i.getPersonPayload());
				if (person1.getFirstName() != null) {
					person.setFirstName(person1.getFirstName());
				}
				if (person1.getLastName() != null) {
					person.setLastName(person.getLastName());
				}
				if (person1.getAddress() != null) {
					if (person.getAddress() != null)
						person.setAddress(jsonToDto.concatWithArrayCopy(person.getAddress(), person1.getAddress()));
					else
						person.setAddress(person1.getAddress());
				}
				if (person1.getPhone() != null) {
					if (person.getPhone() != null)
						person.setPhone(jsonToDto.concatWithArrayCopy(person.getPhone(), person1.getPhone()));
					else
						person.setPhone(person1.getPhone());
				}
				if (person1.getEmail() != null) {
					if (person.getEmail() != null) {
						person.setEmail(jsonToDto.concatWithArrayCopy(person.getEmail(), person1.getEmail()));

					} else
						person.setEmail(person1.getEmail());
				}
			}
		}
		return person;
	}

}
