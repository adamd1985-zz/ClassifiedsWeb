package org.adam;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
@EnableWebMvc
@ComponentScan(value = "org.adam.controller")
public class WebConfig extends WebMvcAutoConfigurationAdapter {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(WebConfig.class);

	// ========================================================================

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		LOGGER.debug("Wiring in view controllers.");

		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
	}

	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());

		super.configureMessageConverters(converters);
	}
}