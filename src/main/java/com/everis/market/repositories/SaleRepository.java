package com.everis.market.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.everis.market.models.Sale;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long> {
	List<Sale> findAll();

	Optional<Sale> findById(Long id);
}
