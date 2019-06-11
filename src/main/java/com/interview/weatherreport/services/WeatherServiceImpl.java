package com.interview.weatherreport.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.weatherreport.entities.Weather;


@Service
public class WeatherServiceImpl implements WeatherService {

	@Value("${endpoint.url}")
	private String endpointURL;

	@Value("${api.key}")
	private String apiKey;

	@Override
	public Weather getWeatherByCountry(String country) throws AplicationException {
		
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<String> entity = new HttpEntity<>(headers);

		try {
			String formatted_URL = MessageFormat.format(endpointURL, country, apiKey);
			ResponseEntity<String> response = restTemplate.exchange(formatted_URL, HttpMethod.GET, entity,
					String.class);
			// maps Jason response to class

			// Check response status an act accordingly

			return MapsJasonToObject(response, country);

		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() != HttpStatus.ACCEPTED) {
				throw new AplicationException("it was not possible to retrieve weather for" + country);
			}

		} catch (IOException ex) {

			throw new AplicationException("it was not possible to retrieve weather for" + country);

		} catch (RestClientException ex) {

			throw new AplicationException("it was not possible to retrieve weather for" + country);

		}

		return null;

	}

	private Weather MapsJasonToObject(ResponseEntity<String> response, String country) throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		Weather theWeather = new Weather();

		JsonNode root = mapper.readTree(response.getBody());

		// Temperature

		JsonNode mainNode = root.path("main");

		if (!mainNode.isMissingNode()) { // if "temp" node is exist
			theWeather.setTemp((float) mainNode.get("temp").asDouble());
		}

		// Sunrise ,Sunset
		JsonNode sysNode = root.path("sys");

		if (!sysNode.isMissingNode()) { // if "sys" node is exist

			long longSunrise = sysNode.get("sunrise").asLong();

			Date thedate = new Date(longSunrise * 1000L);

			theWeather.setSunrise(thedate);

			//

			long longDate = sysNode.get("sunset").asLong();

			Date thedate2 = new Date(longDate * 1000L);

			theWeather.setSunset(thedate2);

			// today'date

			Timestamp ts = new Timestamp(System.currentTimeMillis());

			Date date = new Date(ts.getTime());

			theWeather.setDate(date);

			// Overall Description.

			JsonNode weatherNode = root.path("weather");

			if (weatherNode.isArray()) {

				List<String> description = new ArrayList<>();

				for (JsonNode node : weatherNode) {

					description.add(node.path("description").asText());

				}

				theWeather.setDescription(description);

			}

			// Country
			theWeather.setCountry(country);
		}

		return theWeather;
	}

}
