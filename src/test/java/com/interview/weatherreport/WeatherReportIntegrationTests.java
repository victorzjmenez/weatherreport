package com.interview.weatherreport;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.text.MessageFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class WeatherReportIntegrationTests {

	@Value("${endpoint.url}")
	private String endpointURL;

	@Value("${api.key}")
	private String apiKey;

	@Test
	public void contextLoads() throws IOException {

		RestTemplate testRestTemplate = new RestTemplate();
	
		testRestTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<String> entity = new HttpEntity<>(headers);

		String formatted_URL = MessageFormat.format(endpointURL, "London", apiKey);
		ResponseEntity<String> result = testRestTemplate.exchange(formatted_URL, HttpMethod.GET, entity, String.class);

		assertThat(200).isEqualTo(result.getStatusCodeValue());

		

	}
}
