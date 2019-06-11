package com.interview.weatherreport.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Weather {

	private String country;

	private Date date;

	private List<String> Description = new ArrayList<>();

	private float temp;

	private Date sunrise;

	private Date sunset;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<String> getDescription() {
		return Description;
	}

	public void setDescription(List<String> description) {
		Description = description;
	}

	public float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}

	public Date getSunrise() {
		return sunrise;
	}

	public void setSunrise(Date sunrise) {
		this.sunrise = sunrise;
	}

	public Date getSunset() {
		return sunset;
	}

	public void setSunset(Date sunset) {
		this.sunset = sunset;
	}

	@Override
	public String toString() {
		return "Weather [country=" + country + ", date=" + date + ", Description=" + Description + ", temp=" + temp
				+ ", sunrise=" + sunrise + ", sunset=" + sunset + "]";
	}
	
	
	
	
	
	

	
	
	
	
}
