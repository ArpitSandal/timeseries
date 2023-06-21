package com.infy.timeseries.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.timeseries.dto.PersonDTO;
import com.infy.timeseries.exception.TimeSeriesException;
import com.infy.timeseries.service.TimeSeriesServiceImpl;

@RestController
@RequestMapping(value = "/monitor")
public class TimeSeriesApi {
	
	@Autowired
	TimeSeriesServiceImpl timeSeriesServiceImpl;
	
	@PostMapping
	public ResponseEntity<String> monitor(@RequestBody Object object) throws TimeSeriesException{
		String result=timeSeriesServiceImpl.entry(object);
		return new ResponseEntity<>(result, HttpStatus.CREATED) ;
	}
	
	@GetMapping(value="/{personId}")
	public ResponseEntity<List<Object>> getPerson(@PathVariable Integer personId) throws TimeSeriesException{
		return new ResponseEntity<>(timeSeriesServiceImpl.getAllPerson(personId), HttpStatus.OK);
	}
}
