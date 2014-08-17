package org.adam;

import org.adam.classified.domain.CategoryEnum;
import org.adam.classified.domain.Classified;
import org.adam.classified.domain.repositories.ClassifiedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Main entry point to start up application.
 * 
 * @author adam darmanin
 * @version 1
 */
public class Application {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(Application.class);

	// ========================================================================

	public static void main(String[] args) {
		LOGGER.info("Starting Classifieds App.");

		ConfigurableApplicationContext context = SpringApplication.run(
				new Object[] { RootConfig.class, WebConfig.class,
						JpaConfig.class }, args);

		ClassifiedRepository repository = context
				.getBean(ClassifiedRepository.class);

		// Dummy ads
		repository.save(new Classified("Phone 1", 10, "This is a phone",
				"user@phone.com", "2223333444", "Malaga", CategoryEnum.PHONES));
		repository.save(new Classified("Phone 2", 10, "This is a phone",
				"user@phone.com", "2223333444", "Malaga", CategoryEnum.PHONES));
		repository
				.save(new Classified("Service", 10, "This is a phone",
						"user@phone.com", "2223333444", "Malaga",
						CategoryEnum.SERVICES));
		repository.save(new Classified("Car 1", 10, "This is a phone",
				"user@phone.com", "2223333444", "Malaga", CategoryEnum.CARS));
		repository.save(new Classified("Car 2", 10, "This is a phone",
				"user@phone.com", "2223333444", "Malaga", CategoryEnum.CARS));
	}
}
