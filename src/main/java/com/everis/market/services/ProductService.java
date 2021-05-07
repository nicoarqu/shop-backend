package com.everis.market.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.everis.market.models.Category;
import com.everis.market.models.Product;
import com.everis.market.repositories.CategoryRepository;
import com.everis.market.repositories.ProductRepository;
import com.everis.market.repositories.SaleRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	SaleRepository saleRepository;
	@Autowired
	CategoryRepository categoryRepository;

	// guardar producto sea para crear o editar
	public Product saveProduct(Product product, String[] categoryIds) {
		// agrega listado de categorias
		for (String id : categoryIds) {
			Category categ = categoryRepository.findById(Long.valueOf(id)).get();
			product.addCategory(categ);
		}
		return productRepository.save(product);
	}

	public List<Product> allProducts() {
		return productRepository.findAll();
	}

	public Page<Product> productosPaginados(int numeroPagina, int cantElementos) {

		// obtener todos los productos y ordenarlos por nombre de forma ascendente
		PageRequest pageRequest = PageRequest.of(numeroPagina, cantElementos);
		return productRepository.findAll(pageRequest);
	}

	// obtener un producto por id
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	/**
	 * Editar producto por atributos, retorna true si se modifica
	 */
	public boolean editById(Long id, String name, String code, String price, String description) {
		Optional<Product> productExists = productRepository.findById(id);
		boolean modified = false;
		if (productExists != null) {
			Product product = productExists.get();
			product.setName(name);
			product.setCode(code);
			product.setDescription(description);
			product.setPrice(Long.parseLong(price));
			modified = true;
		}
		return modified;
	}

	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

	// filtros
	public List<Product> findByName(String name) {
		return productRepository.findByNameLike("%" + name + "%");
	}

	// por categoria

}
