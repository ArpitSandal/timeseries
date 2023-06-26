package com.infy.timeseries.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.infy.timeseries.dto.AddressDTO;
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
	public static String personLog = "" ;

//	private static final Log LOGGER = LogFactory.getLog(TimeSeriesServiceImpl.class);

	@Autowired
	Environment env;

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
		return entity.getEventType() + " Person ID " + entity.getPersonId() + " at " + entity.getTimestamp();
	}

	public List<Object> getAllPerson(Integer personId) throws TimeSeriesException {
		List<String> list = repo.findAllByPersonId(personId);
		if (list.isEmpty())
			throw new TimeSeriesException("No such Person Id Found!");
		return jsonToDto.StringToObject(list);
	}

	public Object getPersonQuery(Integer personId, LocalDateTime time) throws TimeSeriesException {

		List<TimeSeriesEntity> list = repo.findPersonQuery(personId, time); // query data from database based on
																			// personId and time

		if (list.isEmpty()) {
			throw new TimeSeriesException("Person Id does not exsist till the given time");
		}

		PersonDTO finalPerson = new PersonDTO(); // will contain the merged person data after all updates and patches
		PersonDTO person; // person data till now
		
		personLog="";

		Path path = Paths.get(env.getProperty("logFilePath"));

		for (TimeSeriesEntity i : list) {
			person = jsonToDto.StringtoDTO(i.getPersonPayload());
			personLog += "Person ID: " + personId + " " + i.getEventType() + " made on: " + i.getProcessedOn() + "\n";
			if (i.getEventType().equalsIgnoreCase("create")) {
				finalPerson = person;
				personLog += finalPerson.toString() + "\n\n";
				continue;
			}
			if (i.getEventType().equalsIgnoreCase("update")) {
				finalPerson = person;
				personLog +=finalPerson.toString() +"\n\n";	
				continue;
			}
			if (person.getFirstName() != null) {
				personLog += jsonToDto.logInfo("First Name", finalPerson.getFirstName(), person.getFirstName());
				finalPerson.setFirstName(person.getFirstName());
			}
			if (person.getLastName() != null) {
				personLog += jsonToDto.logInfo("Last Name", finalPerson.getLastName(), person.getLastName());
				finalPerson.setLastName(person.getLastName());
			}
			if (person.getAddress() != null) {
				AddressDTO[] finalAddress;
				finalAddress = jsonToDto.concatWithArrayCopy(finalPerson.getAddress(), person.getAddress());

				personLog += jsonToDto.logInfo("Address", Arrays.toString(finalPerson.getAddress()),
						Arrays.toString(finalAddress));
				finalPerson.setAddress(finalAddress);

			}
			if (person.getPhone() != null) {
				String[] finalPhone;
				finalPhone = jsonToDto.concatWithArrayCopy(finalPerson.getPhone(), person.getPhone());

				personLog += jsonToDto.logInfo("Phone", Arrays.toString(finalPerson.getPhone()),
						Arrays.toString(finalPhone));
				finalPerson.setPhone(finalPhone);

			}
			if (person.getEmail() != null) {
				String[] finalEmail;
				finalEmail = jsonToDto.concatWithArrayCopy(finalPerson.getEmail(), person.getEmail());
				personLog += jsonToDto.logInfo("Email", Arrays.toString(finalPerson.getEmail()),
						Arrays.toString(finalEmail));
				finalPerson.setEmail(finalEmail);

			}
			personLog += "\n" ;
		}
		personLog += "Final Person with ID: " + finalPerson.getPersonId() + " till " + time + "\n" + finalPerson.toString()
				+ "\n";
		personLog += "------------------\n";
		try {
			Files.writeString(path, personLog, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new TimeSeriesException(e.getMessage());
		}
		return finalPerson;
	}
	
}
