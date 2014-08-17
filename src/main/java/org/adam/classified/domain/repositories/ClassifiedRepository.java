package org.adam.classified.domain.repositories;

import java.util.List;

import org.adam.classified.domain.CategoryEnum;
import org.adam.classified.domain.Classified;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repo for CRUD on Classified database.
 * 
 * @author darmanin adam
 * @version 1
 * @see http://docs.spring.io/spring-data/jpa/docs/current/reference/html/jpa.
 *      repositories.html
 */
@Repository
public interface ClassifiedRepository extends JpaRepository<Classified, Long> {

	/**
	 * Get all classifieds.
	 * 
	 * @return list of {@link Classified}
	 */
	public List<Classified> findByCategory(CategoryEnum category);
}
