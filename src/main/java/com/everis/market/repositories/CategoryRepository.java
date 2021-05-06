package com.everis.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.market.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
