package com.everis.market.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.market.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findAll();

	Optional<Product> findById(Long id);

	List<Product> findByNameLike(String name);
}
