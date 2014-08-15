package org.adam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main entry point to start up application.
 * 
 * @author adam darmanin
 * @version 1
 */
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Application {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(Application.class);

	// ========================================================================

	public static void main(String[] args) {
		LOGGER.info("Starting Classifieds App.");

		SpringApplication.run(Application.class, args);
	}
}
