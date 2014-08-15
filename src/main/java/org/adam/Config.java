package org.adam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class Config extends WebMvcAutoConfigurationAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);

	// ========================================================================

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		LOGGER.debug("Wiring in view controllers.");

		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
	}
}