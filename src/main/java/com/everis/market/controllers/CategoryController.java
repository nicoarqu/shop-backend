package com.everis.market.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.market.models.Category;
import com.everis.market.services.CategoryService;

@Controller
@RequestMapping("/admin")
public class CategoryController {

	// servicio
	@Autowired
	CategoryService categoryService;

	/**
	 * Retorna todos los usuarios
	 */
	@RequestMapping("/categories")
	public String index(Model model) {
		List<Category> allCategories = categoryService.allCategorys();
		model.addAttribute("allCategories", allCategories);
		return "category/list.jsp";
	}
	
	
	/**
	 * Retorna la pagina de registro
	 */
	@RequestMapping("/categories/new")
	public String newCategory(Model model) {
		return "category/new.jsp";
	}

	/**
	 * Retorna la pagina de registro de categorias
	 */
	@RequestMapping("/categories/create")
	public String create(@RequestParam(value = "name") String name, Model model) {
		Category categ = new Category();
		categ.setName(name);
		categoryService.saveCategory(categ);
		return "redirect:/admin/categories/";
	}

	/**
	 * Elimina categoria y vuelve a la lista
	 */
	@RequestMapping("/categories/delete/{id}")
	public String del(@PathVariable Long id) {
		categoryService.deleteById(id);
		return "redirect:/admin/categories";
	}

}
