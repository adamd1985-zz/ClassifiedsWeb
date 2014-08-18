package org.adam.classified.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Simple value object for classifieds.
 *
 * For simplicity it also doubles as a model object.
 * 
 * @author darmanin adam
 * @Version 1
 */
@Entity
public class Classified implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5550831506354713333L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String title;
	private Integer price;
	@NotNull
	private String text;
	@NotNull
	private String email;
	private String phone;
	@NotNull
	private String city;
	@NotNull
	private CategoryEnum category;

	// ========================================================================

	public Classified() {

	}

	public Classified(String title, Integer price, String text, String email,
			String phone, String city, CategoryEnum category) {
		super();
		this.title = title;
		this.price = price;
		this.text = text;
		this.email = email;
		this.phone = phone;
		this.city = city;
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public CategoryEnum getCategory() {
		return category;
	}

	public void setCategory(CategoryEnum category) {
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
