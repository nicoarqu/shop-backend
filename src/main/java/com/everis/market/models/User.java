package com.everis.market.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min = 4, max = 40)
	private String name;
	@Size(min = 2, max = 30)
	private String email;
	@Size(min = 5, max = 15)
	private String password;
	@Size(min = 3, max = 40)
	private String address;

	// constructores
	public User() {
	}

	public User(@Size(min = 4, max = 40) String name, @Size(min = 2, max = 30) String email,
			@Size(min = 5, max = 15) String password, @Size(min = 3, max = 40) String address) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
