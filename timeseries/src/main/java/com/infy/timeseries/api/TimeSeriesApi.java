package com.infy.timeseries.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	
	@PostMapping()
	public ResponseEntity<String> monitor(@RequestBody Object object) throws TimeSeriesException{
		String result=timeSeriesServiceImpl.entry(object);
		return new ResponseEntity<>(result, HttpStatus.CREATED) ;
	}
	
	@GetMapping(value="/{personId}")
	public ResponseEntity<List<Object>> getPerson(@PathVariable Integer personId) throws TimeSeriesException{
		return new ResponseEntity<>(timeSeriesServiceImpl.getAllPerson(personId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/query/{personId}/{time}")
		public ResponseEntity<Object>getPersonQuery(@PathVariable Integer personId, @PathVariable String time) throws TimeSeriesException{
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
			LocalDateTime time1 = LocalDateTime.parse(time,format);
			
//			HttpHeaders header = new HttpHeaders() ;
//			ContentDisposition c=ContentDisposition.builder("inline").filename("compressor.txt").build();
//			header.setContentDisposition(c);	
//			return ResponseEntity.ok().headers(header).contentType(MediaType.APPLICATION_OCTET_STREAM).body(compressedByte);
			return new ResponseEntity<>(timeSeriesServiceImpl.getPersonQuery(personId,time1),HttpStatus.OK);
		}
	@GetMapping(value="/query/new/{personId}/{time}")
	public ResponseEntity<String> getPersonLog(@PathVariable Integer personId , @PathVariable String time) throws TimeSeriesException{
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		LocalDateTime time1 = LocalDateTime.parse(time,format);
		
		HttpHeaders header = new HttpHeaders() ;
		ContentDisposition c=ContentDisposition.builder("inline").filename("timeSeries.txt").build();
		header.setContentDisposition(c);	
		timeSeriesServiceImpl.getPersonQuery(personId,time1) ;
		return ResponseEntity.ok().headers(header).contentType(MediaType.APPLICATION_OCTET_STREAM).body(TimeSeriesServiceImpl.personLog);
	}
}


