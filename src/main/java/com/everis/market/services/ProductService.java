package com.everis.market.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.market.models.Product;
import com.everis.market.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public List<Product> allProducts() {
		return productRepository.findAll();
	}

	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	/**
	 * Editar producto por atributos, retorna true si se modifica
	 */
	public boolean editById(Long id, String name, String code, String price) {
		Optional<Product> productExists = productRepository.findById(id);
		boolean modified = false;
		if (productExists != null) {
			Product product = productExists.get();
			product.setName(name);
			product.setCode(code);
			product.setPrice(Long.parseLong(price));
			modified = true;
		}
		return modified;
	}

	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

}
