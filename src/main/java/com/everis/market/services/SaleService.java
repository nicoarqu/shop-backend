package com.everis.market.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.market.models.Product;
import com.everis.market.models.Sale;
import com.everis.market.models.User;
import com.everis.market.repositories.ProductRepository;
import com.everis.market.repositories.SaleRepository;
import com.everis.market.repositories.UserRepository;

@Service
public class SaleService {
	@Autowired
	SaleRepository saleRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;

	public Sale createSale(Long userId) {
		Sale sale = new Sale();
		User buyer = userRepository.findById(userId).get();
		sale.setBuyer(buyer);
		return saleRepository.save(sale);
	}

	public Sale saveSale(Sale sale) {
		return saleRepository.save(sale);
	}

	public List<Sale> allSales() {
		return saleRepository.findAll();
	}

	public Optional<Sale> findById(Long id) {
		return saleRepository.findById(id);
	}

	/**
	 * Guardar producto en lista de compras
	 */
	public Sale addProductToSale(Long productId, Long saleId) {
		Product product = productRepository.findById(productId).get();
		Sale sale = saleRepository.findById(saleId).get();
		sale.addProduct(product);
		return saleRepository.save(sale);
	}

}
