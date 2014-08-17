package org.adam.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Administration and diagnostics rest controller.
 * 
 * @author darmanin adam
 * @version 1
 */
@RestController("AdminController")
@RequestMapping("/server")
public class AdminController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AdminController.class);

	// ========================================================================

	/**
	 * Constructor.
	 */
	public AdminController() {
		LOGGER.debug("Server is running");
	}

	/**
	 * An isAlive check on this server done by any external entity.
	 * 
	 * @return "1" the heart beat.
	 */
	@RequestMapping("/heartbeat")
	public String isAlive() {
		LOGGER.debug("Heartbeat checked");
		return "1";
	}

	/**
	 * Should return REST contract documentation.
	 * 
	 * @return REST contracts.
	 */
	@RequestMapping("/")
	public String getUsage() {
		return "TODO";
	}

}
