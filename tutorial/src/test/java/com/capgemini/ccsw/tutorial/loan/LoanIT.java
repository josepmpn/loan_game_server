package com.capgemini.ccsw.tutorial.loan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.capgemini.ccsw.tutorial.customer.model.CustomerDto;
import com.capgemini.ccsw.tutorial.game.model.GameDto;
import com.capgemini.ccsw.tutorial.loan.model.LoanDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LoanIT {

	public static final String LOCALHOST = "http://localhost:";
	public static final String SERVICE_PATH = "/loan/";;

	private static final String GAME_PARAM = "idGame";
	private static final String CUSTOMER_PARAM = "idCustomer";
	private static final String LOAN_DATE = "loanDate";
	private static final String PAGE_NUMBER = "pageNumber";
	private static final String PAGE_SIZE = "pageSize";

	private static final Long EXISTING_GAME_ID = 6L;
	private static final Long EXISTING_CUSTOMER_ID = 2L;

	@SuppressWarnings("deprecation")
	private static final Date EXISTING_LOAN_DATE = new Date();

	private static final Long NO_EXISTING_GAME_ID = 9L;
	private static final Long NO_EXISTING_CUSTOMER_ID = 7L;

	@SuppressWarnings("deprecation")
	private static final Date NO_EXISTING_LOAN_DATE = new Date();

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	ParameterizedTypeReference<Page<LoanDto>> responseTypePage = new ParameterizedTypeReference<Page<LoanDto>>() {
	};

	ParameterizedTypeReference<Page<LoanDto>> responseTypePagePage = new ParameterizedTypeReference<Page<LoanDto>>() {
	};

	@Test
	public void findWithoutFiltersShouldReturnAllLoansInDB() {

		int LOANS_WITH_FILTER = 6;
		long TOTAL_lOANS = 6;

		Map<String, Object> params = new HashMap<>();
		params.put(GAME_PARAM, null);
		params.put(CUSTOMER_PARAM, null);
		params.put(LOAN_DATE, null);
		params.put(PAGE_NUMBER, 0);
		params.put(PAGE_SIZE, 5);

		ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET,
				null, responseTypePagePage, params);

		assertNotNull(response);
		assertEquals(TOTAL_lOANS, response.getBody().getTotalElements());
		assertEquals(LOANS_WITH_FILTER, response.getBody().getContent().size());
	}

	@Test
	public void findExistsGameShouldReturnLoans() {

		int LOANS_WITH_FILTER = 1;
		long TOTAL_lOANS = 5L;

		Map<String, Object> params = new HashMap<>();
		params.put(GAME_PARAM, EXISTING_GAME_ID);
		params.put(CUSTOMER_PARAM, null);
		params.put(LOAN_DATE, null);
		params.put(PAGE_NUMBER, 0);
		params.put(PAGE_SIZE, 5);

		ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET,
				null, responseTypePage, params);

		assertNotNull(response);
		assertEquals(TOTAL_lOANS, response.getBody().getTotalElements());
		assertEquals(LOANS_WITH_FILTER, response.getBody().getContent().size());
	}

	@Test
	public void findExistsCustomShouldReturnLoans() {

		int LOANS_WITH_FILTER = 3;
		long TOTAL_lOANS = 5;

		Map<String, Object> params = new HashMap<>();
		params.put(GAME_PARAM, null);
		params.put(CUSTOMER_PARAM, EXISTING_CUSTOMER_ID);
		params.put(LOAN_DATE, null);
		params.put(PAGE_NUMBER, 0);
		params.put(PAGE_SIZE, 5);

		ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET,
				null, responseTypePage, params);

		assertNotNull(response);
		assertEquals(TOTAL_lOANS, response.getBody().getTotalElements());
		assertEquals(LOANS_WITH_FILTER, response.getBody().getContent().size());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void findExistsLoanDateShouldReturnLoans() {

		int LOANS_WITH_FILTER = 5;
		Long TOTAL_lOANS = 5L;

		EXISTING_LOAN_DATE.setDate(15);
		EXISTING_LOAN_DATE.setMonth(5);
		EXISTING_LOAN_DATE.setYear(2022);

		Map<String, Object> params = new HashMap<>();
		params.put(GAME_PARAM, null);
		params.put(CUSTOMER_PARAM, null);
		params.put(LOAN_DATE, EXISTING_LOAN_DATE);
		params.put(PAGE_NUMBER, 0);
		params.put(PAGE_SIZE, 5);

		ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET,
				null, responseTypePage, params);

		assertNotNull(response);
		assertEquals(TOTAL_lOANS, response.getBody().getTotalElements());
		assertEquals(LOANS_WITH_FILTER, response.getBody().getContent().size());
	}

	@Test
	public void findExistsGameAndCustomerShouldReturnLoans() {

		int LOANS_WITH_FILTER = 1;
		Long TOTAL_lOANS = 5L;

		Map<String, Object> params = new HashMap<>();
		params.put(GAME_PARAM, EXISTING_GAME_ID);
		params.put(CUSTOMER_PARAM, EXISTING_CUSTOMER_ID);
		params.put(LOAN_DATE, null);
		params.put(PAGE_NUMBER, 0);
		params.put(PAGE_SIZE, 5);

		ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET,
				null, responseTypePage, params);

		assertNotNull(response);
		assertEquals(TOTAL_lOANS, response.getBody().getTotalElements());
		assertEquals(LOANS_WITH_FILTER, response.getBody().getContent().size());
	}

	@Test
	public void findExistsGameAndLoanDateShouldReturnLoans() {

		int LOANS_WITH_FILTER = 5;
		Long TOTAL_lOANS = 5L;

		EXISTING_LOAN_DATE.setDate(15);
		EXISTING_LOAN_DATE.setMonth(5);
		EXISTING_LOAN_DATE.setYear(2022);

		Map<String, Object> params = new HashMap<>();
		params.put(GAME_PARAM, EXISTING_GAME_ID);
		params.put(CUSTOMER_PARAM, null);
		params.put(LOAN_DATE, EXISTING_LOAN_DATE);
		params.put(PAGE_NUMBER, 0);
		params.put(PAGE_SIZE, 5);

		ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET,
				null, responseTypePage, params);

		assertNotNull(response);
		assertEquals(TOTAL_lOANS, response.getBody().getTotalElements());
		assertEquals(LOANS_WITH_FILTER, response.getBody().getContent().size());
	}

	@Test
	public void findExistsCustomerAndLoanDateShouldReturnLoans() {

		int LOANS_WITH_FILTER = 5;
		Long TOTAL_lOANS = 5L;

		EXISTING_LOAN_DATE.setDate(15);
		EXISTING_LOAN_DATE.setMonth(5);
		EXISTING_LOAN_DATE.setYear(2022);

		Map<String, Object> params = new HashMap<>();
		params.put(GAME_PARAM, null);
		params.put(CUSTOMER_PARAM, EXISTING_CUSTOMER_ID);
		params.put(LOAN_DATE, EXISTING_LOAN_DATE);
		params.put(PAGE_NUMBER, 0);
		params.put(PAGE_SIZE, 5);

		ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET,
				null, responseTypePage, params);

		assertNotNull(response);
		assertEquals(TOTAL_lOANS, response.getBody().getTotalElements());
		assertEquals(LOANS_WITH_FILTER, response.getBody().getContent().size());
	}

	@Test
	public void findNotExistsGameShouldReturnEmpty() {

		int LOANS_WITH_FILTER = 5;
		Long TOTAL_lOANS = 5L;

		Map<String, Object> params = new HashMap<>();
		params.put(GAME_PARAM, NO_EXISTING_GAME_ID);
		params.put(CUSTOMER_PARAM, null);
		params.put(LOAN_DATE, null);
		params.put(PAGE_NUMBER, 0);
		params.put(PAGE_SIZE, 5);

		ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET,
				null, responseTypePage, params);

		assertNotNull(response);
		assertEquals(TOTAL_lOANS, response.getBody().getTotalElements());
		assertEquals(LOANS_WITH_FILTER, response.getBody().getContent().size());
	}

	@Test
	public void findNotExistsCustomerShouldReturnEmpty() {

		int LOANS_WITH_FILTER = 5;
		Long TOTAL_lOANS = 5L;

		Map<String, Object> params = new HashMap<>();
		params.put(GAME_PARAM, null);
		params.put(CUSTOMER_PARAM, NO_EXISTING_CUSTOMER_ID);
		params.put(LOAN_DATE, null);
		params.put(PAGE_NUMBER, 0);
		params.put(PAGE_SIZE, 5);

		ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET,
				null, responseTypePage, params);

		assertNotNull(response);
		assertEquals(TOTAL_lOANS, response.getBody().getTotalElements());
		assertEquals(LOANS_WITH_FILTER, response.getBody().getContent().size());
	}

	@Test
	public void findNotExistsTitleOrCategoryOrLoanDateShouldReturnEmpty() {

		int LOANS_WITH_FILTER = 5;
		Long TOTAL_lOANS = 5L;

		NO_EXISTING_LOAN_DATE.setDate(1);
		NO_EXISTING_LOAN_DATE.setMonth(6);
		NO_EXISTING_LOAN_DATE.setYear(2022);

		Map<String, Object> params = new HashMap<>();
		params.put(GAME_PARAM, NO_EXISTING_GAME_ID);
		params.put(CUSTOMER_PARAM, NO_EXISTING_CUSTOMER_ID);
		params.put(LOAN_DATE, NO_EXISTING_LOAN_DATE);
		params.put(PAGE_NUMBER, 0);
		params.put(PAGE_SIZE, 5);

		ResponseEntity<Page<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET,
				null, responseTypePage, params);
		assertNotNull(response);
		assertEquals(TOTAL_lOANS, response.getBody().getTotalElements());
		assertEquals(LOANS_WITH_FILTER, response.getBody().getContent().size());

		params.put(GAME_PARAM, NO_EXISTING_GAME_ID);
		params.put(CUSTOMER_PARAM, EXISTING_CUSTOMER_ID);
		params.put(LOAN_DATE, NO_EXISTING_LOAN_DATE);
		params.put(PAGE_NUMBER, 0);
		params.put(PAGE_SIZE, 5);

		response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseTypePage,
				params);
		assertNotNull(response);
		assertEquals(TOTAL_lOANS, response.getBody().getTotalElements());
		assertEquals(LOANS_WITH_FILTER, response.getBody().getContent().size());

		params.put(GAME_PARAM, NO_EXISTING_GAME_ID);
		params.put(CUSTOMER_PARAM, EXISTING_CUSTOMER_ID);
		params.put(LOAN_DATE, EXISTING_LOAN_DATE);
		params.put(PAGE_NUMBER, 0);
		params.put(PAGE_SIZE, 5);

		response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseTypePage,
				params);
		assertNotNull(response);
		assertEquals(TOTAL_lOANS, response.getBody().getTotalElements());
		assertEquals(LOANS_WITH_FILTER, response.getBody().getContent().size());

		params.put(GAME_PARAM, NO_EXISTING_GAME_ID);
		params.put(CUSTOMER_PARAM, NO_EXISTING_CUSTOMER_ID);
		params.put(LOAN_DATE, EXISTING_LOAN_DATE);
		params.put(PAGE_NUMBER, 0);
		params.put(PAGE_SIZE, 5);

		response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseTypePage,
				params);
		assertNotNull(response);
		assertEquals(TOTAL_lOANS, response.getBody().getTotalElements());
		assertEquals(LOANS_WITH_FILTER, response.getBody().getContent().size());

		params.put(GAME_PARAM, EXISTING_GAME_ID);
		params.put(CUSTOMER_PARAM, NO_EXISTING_CUSTOMER_ID);
		params.put(LOAN_DATE, NO_EXISTING_LOAN_DATE);
		params.put(PAGE_NUMBER, 0);
		params.put(PAGE_SIZE, 5);

		response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseTypePage,
				params);
		assertNotNull(response);
		assertEquals(TOTAL_lOANS, response.getBody().getTotalElements());
		assertEquals(LOANS_WITH_FILTER, response.getBody().getContent().size());

		params.put(GAME_PARAM, EXISTING_GAME_ID);
		params.put(CUSTOMER_PARAM, NO_EXISTING_CUSTOMER_ID);
		params.put(LOAN_DATE, EXISTING_LOAN_DATE);
		params.put(PAGE_NUMBER, 0);
		params.put(PAGE_SIZE, 5);

		response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseTypePage,
				params);
		assertNotNull(response);
		assertEquals(TOTAL_lOANS, response.getBody().getTotalElements());
		assertEquals(LOANS_WITH_FILTER, response.getBody().getContent().size());

		params.put(GAME_PARAM, EXISTING_GAME_ID);
		params.put(CUSTOMER_PARAM, NO_EXISTING_CUSTOMER_ID);
		params.put(LOAN_DATE, NO_EXISTING_LOAN_DATE);
		params.put(PAGE_NUMBER, 0);
		params.put(PAGE_SIZE, 5);

		response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET, null, responseTypePage,
				params);
		assertNotNull(response);
		assertEquals(TOTAL_lOANS, response.getBody().getTotalElements());
		assertEquals(LOANS_WITH_FILTER, response.getBody().getContent().size());

	}

	@Test
	public void saveWithoutIdShouldCreateNewLoan() {

		LoanDto dto = new LoanDto();
		GameDto gameDto = new GameDto();
		gameDto.setId(5L);

		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(5L);

		Date startDate = new Date();
		startDate.setDate(15);
		startDate.setMonth(5);
		startDate.setYear(2022);

		Date endDate = new Date();
		endDate.setDate(15);
		endDate.setMonth(5);
		endDate.setYear(2022);

		dto.setGame(gameDto);
		dto.setCustomer(customerDto);
		dto.setStart(startDate);
		dto.setEnd(endDate);

		ResponseEntity<Boolean> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT,
				new HttpEntity<>(dto), Boolean.class);

		assertNotNull(response);
		assertEquals(false, response.getBody());
	}

	@Test
	public void saveWithoutIdNotShouldCreateNewLoan() {

		LoanDto dto = new LoanDto();
		GameDto gameDto = new GameDto();
		gameDto.setId(3L);

		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(4L);

		Date startDate = new Date();
		startDate.setDate(15);
		startDate.setMonth(5);
		startDate.setYear(2022);

		Date endDate = new Date();
		endDate.setDate(15);
		endDate.setMonth(5);
		endDate.setYear(2022);

		dto.setGame(gameDto);
		dto.setCustomer(customerDto);
		dto.setStart(startDate);
		dto.setEnd(endDate);

		ResponseEntity<Boolean> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT,
				new HttpEntity<>(dto), Boolean.class);

		assertNotNull(response);
		assertEquals(false, response.getBody());
	}

}
