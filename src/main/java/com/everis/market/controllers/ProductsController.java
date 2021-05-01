package com.everis.market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.market.models.Product;
import com.everis.market.services.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductsController {

	// servicio
	@Autowired
	ProductService productService;

	/**
	 * Retorna todos los usuarios
	 */
	@RequestMapping("/products")
	public String index(Model model) {
		List<Product> allProducts = productService.allProducts();
		model.addAttribute("allProducts", allProducts);
		return "product/list.jsp";
	}

	/**
	 * Retorna la pagina de registro
	 */
	@RequestMapping("/products/new")
	public String newProduct(Model model) {
		return "product/new.jsp";
	}

	/**
	 * Retorna la pagina de registro
	 */
	@RequestMapping("/products/create")
	public String create(@RequestParam(value = "name") String name, @RequestParam(value = "code") String code,
			@RequestParam(value = "price") String price,
			Model model) {
		// crear usuario y retorna id
		Product product = new Product();
		product.setName(name);
		product.setCode(code);
		product.setPrice(Long.parseLong(price));

		Product inserted = productService.saveProduct(product);
		String productId = Long.toString(inserted.getId());
		return "redirect:/products/show/" + productId;
	}

	/**
	 * Retorna la pagina de registro
	 */
	@RequestMapping("/products/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Optional<Product> productExists = productService.findById(id);
		if (productExists != null) {
			Product product = productExists.get();
			model.addAttribute("name", product.getName());
			model.addAttribute("code", product.getCode());
			model.addAttribute("price", product.getPrice());
			return "product/edit.jsp";
		}
		return "product/list.jsp";
	}

	/**
	 * Retorna la pagina de editar usuario, devuelve el usuario con los nuevos
	 * cambios si tiene exito
	 */
	@RequestMapping("/products/update/{id}")
	public String update(@RequestParam(value = "name") String name, @RequestParam(value = "code") String code,
			@RequestParam(value = "price") String price,
			@PathVariable Long id, Model model) {
		Optional<Product> productExists = productService.findById(id);
		if (productExists != null) {
			Product product = productExists.get();
			product.setName(name);
			product.setName(name);
			product.setCode(code);
			product.setPrice(Long.parseLong(price));

			productService.saveProduct(product);
			String productId = Long.toString(id);
			return "redirect:/products/show/" + productId;

		}

		return "redirect:/products/edit/"+id;
	}

	/**
	 * Retorna la pagina de mostrar usuario
	 */
	@RequestMapping("/products/show/{id}")
	public String show(@PathVariable Long id, Model model) {
		Optional<Product> productExists = productService.findById(id);
		if (productExists != null) {
			Product product = productExists.get();
			model.addAttribute("name", product.getName());
			model.addAttribute("code", product.getCode());
			model.addAttribute("price", product.getPrice());
			return "product/show.jsp";
		}
		return "redirect:/products";
	}

	/**
	 * Elimina usuario y vuelve a la lista
	 */
	@RequestMapping("/products/delete/{id}")
	public String del(@PathVariable Long id, Model model) {
		productService.deleteById(id);
		return "redirect:/products";
	}
}
