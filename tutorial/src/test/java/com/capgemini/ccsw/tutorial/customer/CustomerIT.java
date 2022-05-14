package com.capgemini.ccsw.tutorial.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.capgemini.ccsw.tutorial.customer.model.CustomerDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CustomerIT {

	public static final String LOCALHOST = "http://localhost:";
	public static final String SERVICE_PATH = "/customer/";

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	ParameterizedTypeReference<List<CustomerDto>> responseType = new ParameterizedTypeReference<List<CustomerDto>>() {
	};

	@Test
	public void findAllShouldReturnAllCustomers() {

		ResponseEntity<List<CustomerDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
				HttpMethod.GET, null, responseType);

		assertNotNull(response);
		assertEquals(3, response.getBody().size());
	}

	public static final Long NEW_CUSTOMER_ID = 6L;
	public static final String NEW_CUSTOMER_NAME = "Amalia Rubio";

	@Test
	public void saveWithoutIdShouldCreateNewCustomer() {

		CustomerDto dto = new CustomerDto();
		dto.setName(NEW_CUSTOMER_NAME);

		restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), boolean.class);

		ResponseEntity<List<CustomerDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
				HttpMethod.GET, null, responseType);
		assertNotNull(response);
		assertEquals(4, response.getBody().size());

		CustomerDto categorySearch = response.getBody().stream().filter(item -> item.getId().equals(NEW_CUSTOMER_ID))
				.findFirst().orElse(null);
		assertNotNull(categorySearch);
		assertEquals(NEW_CUSTOMER_NAME, categorySearch.getName());
	}

	public static final Long MODIFY_CUSTOMER_ID = 3L;

	@Test
	public void modifyWithExistIdShouldModifyCustomer() {

		CustomerDto dto = new CustomerDto();
		dto.setName(NEW_CUSTOMER_NAME);

		restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + MODIFY_CUSTOMER_ID, HttpMethod.PUT,
				new HttpEntity<>(dto), boolean.class);

		ResponseEntity<List<CustomerDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
				HttpMethod.GET, null, responseType);
		assertNotNull(response);
		assertEquals(3, response.getBody().size());

		CustomerDto categorySearch = response.getBody().stream().filter(item -> item.getId().equals(MODIFY_CUSTOMER_ID))
				.findFirst().orElse(null);
		assertNotNull(categorySearch);
		assertEquals(NEW_CUSTOMER_NAME, categorySearch.getName());
	}

	public static final Long DELETE_CUSTOMER_ID = 2L;

	@Test
	public void deleteWithExistsIdShouldDeleteCustomer() {

		restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + DELETE_CUSTOMER_ID, HttpMethod.DELETE, null,
				Void.class);

		ResponseEntity<List<CustomerDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
				HttpMethod.GET, null, responseType);
		assertNotNull(response);
		assertEquals(2, response.getBody().size());
	}

}
