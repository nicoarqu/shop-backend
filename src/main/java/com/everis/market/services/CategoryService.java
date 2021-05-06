package com.everis.market.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.market.models.Category;
import com.everis.market.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	// guardar categoryo sea crear o editar
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	public List<Category> allCategorys() {
		return categoryRepository.findAll();
	}

	public void deleteById(Long id) {
		categoryRepository.deleteById(id);

	}

}
