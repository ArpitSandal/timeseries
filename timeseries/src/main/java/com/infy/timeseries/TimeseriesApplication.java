package com.infy.timeseries;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class TimeseriesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TimeseriesApplication.class, args);
	}

	@Override
	public void run(String... args) {
	}
	
}
