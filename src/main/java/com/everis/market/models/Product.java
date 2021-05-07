package com.everis.market.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String code;
	private Long price;
	private String description;
	private String url;

	// relaciones
	// relaciones productos comprados en cada venta
	@ManyToMany(mappedBy = "cart_products")
	private Set<Sale> shop_sales = new HashSet<>();
	// relaciones productos con categorias
	@ManyToMany
	@JoinTable(name = "categories_products", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();

	// constructores
	public Product() {
	}

	public Product(String name, String code, Long price, String description) {
		super();
		this.name = name;
		this.code = code;
		this.price = price;
		this.description = description;
	}

	// con URL
	public Product(String name, String code, Long price, String description, String url) {
		super();
		this.name = name;
		this.code = code;
		this.price = price;
		this.description = description;
		this.url = url;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Sale> getCart_products() {
		return shop_sales;
	}

	public void setCart_products(Set<Sale> cart_products) {
		this.shop_sales = cart_products;
	}

	public Set<Sale> getShop_sales() {
		return this.shop_sales;
	}

	public void setShop_sales(Set<Sale> shop_sales) {
		this.shop_sales = shop_sales;
	}

	public Set<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	// agrega venta a producto
	public void addSale(Sale sale) {
		this.shop_sales.add(sale);
	}

	// agrega categoria a producto
	public void addCategory(Category category) {
		this.categories.add(category);
	}

}
