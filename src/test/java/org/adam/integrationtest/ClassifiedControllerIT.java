package org.adam.integrationtest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.adam.classified.domain.CategoryEnum;
import org.adam.classified.domain.Classified;
import org.adam.classified.domain.repositories.ClassifiedRepository;
import org.adam.controller.ClassifiedController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Middleware integration test of this server.
 * 
 * Test rest contracts.
 * 
 * Note how we create a context and inject mocks within this context.
 * 
 * @author darmanin adam
 * @version 1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = { TestWebConfig.class,
		ClassifiedControllerIT.class })
@Configuration
public class ClassifiedControllerIT {
	private MockMvc mockMvc;

	/**
	 * This can also be injected at bean creation, but we let spring do the
	 * wiring.
	 */
	@Mock
	private ClassifiedRepository mockRepo;

	private List<Classified> classifieds;

	@Autowired
	private WebApplicationContext ctxt;

	@Autowired
	@InjectMocks
	private ClassifiedController controller;

	// ========================================================================

	/**
	 * We just specify the controller we want at the start of this test. Since
	 * this test is also a bean class we can inject mocks and targeted beans to
	 * test.
	 * 
	 * @return The bean, it will be additionally wired by SPRING using either
	 *         lazy loaded proxies or instantiate the proper beans required.
	 */
	@Bean
	public ClassifiedController getClassifiedController() {
		return new ClassifiedController();
	}

	/**
	 * Sets up and injects mocks, creates dummy test data.
	 */
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.webAppContextSetup(ctxt).build();

		classifieds = new ArrayList<Classified>();

		classifieds.add(new Classified("Phone 1", 10, "This is a phone",
				"user@phone.com", "2223333444", "Malaga", CategoryEnum.PHONES));
		classifieds.add(new Classified("Phone 2", 10, "This is a phone",
				"user@phone.com", "2223333444", "Malaga", CategoryEnum.PHONES));
		classifieds
				.add(new Classified("Service", 10, "This is a phone",
						"user@phone.com", "2223333444", "Malaga",
						CategoryEnum.SERVICES));
		classifieds.add(new Classified("Car 1", 10, "This is a phone",
				"user@phone.com", "2223333444", "Malaga", CategoryEnum.CARS));
		classifieds.add(new Classified("Car 2", 10, "This is a phone",
				"user@phone.com", "2223333444", "Malaga", CategoryEnum.CARS));
	}

	/**
	 * Test the get list REST contract.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetListContract() throws Exception {
		Mockito.when(mockRepo.findAll()).thenReturn(classifieds);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(classifieds);

		mockMvc.perform(MockMvcRequestBuilders.get("/classifieds"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(json));

		Mockito.verify(mockRepo).findAll();

	}

	/**
	 * Test the get category list REST contract.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetCatergoryList() throws Exception {

		List<Classified> categoryList = new ArrayList<Classified>();

		for (Classified category : classifieds) {
			if (category.equals(CategoryEnum.SERVICES)) {
				categoryList.add(category);
			}
		}

		Mockito.when(mockRepo.findByCategory(CategoryEnum.SERVICES))
				.thenReturn(categoryList);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(categoryList);

		mockMvc.perform(
				MockMvcRequestBuilders.get(
						"/classifieds/categories/{category}",
						CategoryEnum.SERVICES)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(json));

		Mockito.verify(mockRepo).findByCategory(CategoryEnum.SERVICES);

	}

	/**
	 * Test the ad creation REST contract.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateClassified() throws Exception {

		Classified newClassified = new Classified("new ad", 10,
				"This is a new ad", "user@phone.com", "2223333444",
				"Gibraltar", CategoryEnum.CARS);

		// Only for tests we set the ID, the JPA layer should do this for us.
		newClassified.setId(999L);

		Mockito.when(mockRepo.save(Mockito.isA(Classified.class))).thenReturn(
				newClassified);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(newClassified);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/classifieds")
						.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.content().string("999"));

		Mockito.verify(mockRepo).save(Mockito.isA(Classified.class));

	}
}