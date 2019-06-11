package com.interview.weatherreport.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.interview.weatherreport.entities.Weather;
import com.interview.weatherreport.services.AplicationException;
import com.interview.weatherreport.services.WeatherService;



@Controller
public class WeatherController {

	
	@Autowired
	private WeatherService weatherService;
	
	@RequestMapping("/showWeather")
	public String showWheather() {
		
		return "findWeather";
		
	}
	
	@RequestMapping("/getWeather")
	public String findWeather(@RequestParam("country") String country, ModelMap modelMap) {
		
		// Call weather Service
		
		Weather theWeather = null;
		try {
			theWeather = weatherService.getWeatherByCountry(country);
		} catch (AplicationException e) {
			
			String message=e.getMessage();
			modelMap.addAttribute("message", message);
			return "findWeather";
		}
		
		modelMap.addAttribute("weather", theWeather);
		
		return "displayWeather";
	}
	
	
	
}
