package org.adam.integrationtest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.adam.controller.AdminController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Middle integration test of this server.
 * 
 * @author darmanin adam
 * @version 1
 */
public class AdminControllerIT {
	private MockMvc mockMvc;

	@InjectMocks
	private AdminController controller;

	// ========================================================================

	/**
	 * This servlet is so simple it doesn't require a context to exist.
	 */
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		mockMvc = standaloneSetup(controller).build();
	}

	/**
	 * Tests if heartbeat is requested, given that the server is running it
	 * should return "1".
	 * 
	 * @throws Exception
	 */
	@Test
	public void testHeartBeatContract() throws Exception {
		mockMvc.perform(get("/server/heartbeat")).andExpect(
				content().string("1"));

	}

}