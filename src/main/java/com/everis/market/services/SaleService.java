package com.everis.market.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.market.models.Sale;
import com.everis.market.repositories.SaleRepository;

@Service
public class SaleService {
	@Autowired
	SaleRepository saleRepository;

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
	 * Editar usuario por atributos, retorna true si se modifica
	 */
	public boolean editById(Long id, String buyer, String total) {
		Optional<Sale> saleExists = saleRepository.findById(id);
		boolean modified = false;
		if (saleExists != null) {
			Sale sale = saleExists.get();
			sale.setBuyer(buyer);
			sale.setTotal(Integer.valueOf(total));
			modified = true;
		}
		return modified;
	}

	public void deleteById(Long id) {
		saleRepository.deleteById(id);
	}

}
