package com.interview.weatherreport.services;

import org.springframework.stereotype.Service;

import com.interview.weatherreport.entities.Weather;


@Service
public interface WeatherService {

	Weather getWeatherByCountry(String country) throws AplicationException;
	
	
}
