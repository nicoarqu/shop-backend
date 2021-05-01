package com.everis.market.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min = 4, max = 40)
	private String name;
	@Size(min = 2, max = 30)
	private String code;
	@Min(3)
	@Max(999999)
	private Long price;

	// constructores
	public Product() {
	}

	public Product(@Size(min = 4, max = 40) String name, @Size(min = 2, max = 30) String code,
			@Min(3) @Max(999999) Long price) {
		super();
		this.name = name;
		this.code = code;
		this.price = price;
	}

	// getters, setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

}
