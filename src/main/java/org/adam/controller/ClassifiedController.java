package org.adam.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.adam.classified.domain.CategoryEnum;
import org.adam.classified.domain.Classified;
import org.adam.classified.domain.repositories.ClassifiedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * Classifieds rest controller.
 * 
 * @author darmanin adam
 * @version 1
 */
@RestController("ClassifiedController")
public class ClassifiedController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ClassifiedController.class);

	/** persistence calls done to this. */
	private ClassifiedRepository repository;

	// ========================================================================

	/**
	 * Constructor.
	 */
	public ClassifiedController() {
		LOGGER.debug("Server is running");
	}

	/**
	 * Gets a list of all available classified.
	 * 
	 * @return list of {@link Classified}.
	 */
	@RequestMapping("/classifieds")
	public List<Classified> getClasifiedList() throws HttpServerErrorException {
		LOGGER.debug("Get Classified List");

		try {
			return repository.findAll();
		} catch (Exception e) {
			LOGGER.error("Persistence lookup error!", e);
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets a list of all available classified of a given category
	 * 
	 * @return list of {@link Classified}.
	 */
	@RequestMapping("/classifieds/categories/{category}")
	public List<Classified> getClasifiedList(@PathVariable String category)
			throws HttpServerErrorException {
		LOGGER.debug("Get Classified List of category: {}", category);

		CategoryEnum categoryEnum = null;
		try {
			categoryEnum = CategoryEnum.valueOf(category);
		} catch (IllegalArgumentException e) {
			LOGGER.warn("Malformed request was done for category {}", category);
		}
		if (categoryEnum == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND,
					"That category doesn't exist");
		}

		try {
			return repository.findByCategory(categoryEnum);
		} catch (Exception e) {
			LOGGER.error("Persistence lookup error!", e);
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Creates a new classified within the repository.
	 * 
	 * @return The ID of the new classified to be searched again later on.
	 */
	@RequestMapping(value = "/classifieds", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Long saveClassified(@RequestBody Classified classified)
			throws HttpServerErrorException {
		LOGGER.debug("Save Classified of : {}", classified);

		try {
			return repository.save(classified).getId();
		} catch (Exception e) {
			LOGGER.error("Persistence error!", e);
			throw new HttpServerErrorException(
					HttpStatus.INTERNAL_SERVER_ERROR,
					"Error persisting classified: " + e.getMessage());
		}
	}

	/**
	 * This exception handler resolves exception into proper REST returns.
	 * 
	 * @param ex
	 * @param resonse
	 * @return
	 */
	@ExceptionHandler(value = { HttpStatusCodeException.class })
	public String convertExceptionToStatus(HttpStatusCodeException ex,
			HttpServletResponse resonse) {
		resonse.setStatus(ex.getStatusCode().value());
		return ex.getMessage();
	}

	// ------------------------------------------------------------------------

	/**
	 * Note this is lazy loaded to allow us further on in the context to define
	 * a mock, an embedded or an external DB At runtime. Should also make
	 * initialisation of this servlet faster if the persistence is not yet
	 * initialised.
	 * 
	 * @param repository
	 */
	@Lazy
	@Autowired
	public void setRepository(ClassifiedRepository repository) {
		this.repository = repository;
	}
}
