package org.adam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(value = "org.adam.classified.domain.**.* ")
public class JpaConfig {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(JpaConfig.class);

	// ========================================================================

}