package com.everis.market.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.market.models.Product;
import com.everis.market.services.CategoryService;
import com.everis.market.services.ProductService;

@Controller
public class ProductsController {

	// servicio
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;

	private static final int CANT_PRODUCTOS = 2;

	/**
	 * Retorna los productos paginados
	 */
	@RequestMapping("/products")
	public String index(Model model) {
		List<Product> allProducts = productService.allProducts();
		Page<Product> products = productService.productosPaginados(0, CANT_PRODUCTOS);
		int totalPages = products.getTotalPages();
		model.addAttribute("allProducts", allProducts);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("products", products.getContent());

		return "product/list.jsp";
	}

	/**
	 * Paginacion por pageNumber
	 */
	@RequestMapping("/products/page/{pageNumber}")
	public String getProductosPagina(@PathVariable("pageNumber") int pageNumber, Model model) {
		// paginas iterable comienzan en 0 cero. 1 a maxPag (ultima pagina)
		Page<Product> products = productService.productosPaginados(pageNumber - 1, CANT_PRODUCTOS);
		int totalPages = products.getTotalPages();
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("products", products.getContent());

		return "product/list.jsp";
	}

	/**
	 * Filtra productos por nombre
	 */
	@RequestMapping("/products/name")
	public String filterByName(@RequestParam(value = "name") String name, Model model) {
		List<Product> allProducts = productService.findByName(name);
		model.addAttribute("products", allProducts);
		return "product/list.jsp";
	}

	/**
	 * Retorna la pagina de registro
	 */
	@RequestMapping("/products/new")
	public String newProduct(HttpSession session, Model model) {
		Integer currentRole = (Integer) session.getAttribute("currentRole");
		if (currentRole <= 1) {
			return "redirect:/products";
		}
		model.addAttribute("categories", categoryService.allCategorys());
		return "product/new.jsp";

	}

	/**
	 * Retorna la pagina de registro de productos
	 */
	@RequestMapping("/products/create")
	public String create(@RequestParam(value = "name") String name, @RequestParam(value = "code") String code,
			@RequestParam(value = "price") String price, @RequestParam(value = "description") String description,
			@RequestParam("categories") String[] categoryIds, HttpSession session, Model model) {
		Integer currentRole = (Integer) session.getAttribute("currentRole");
		if (currentRole <= 1) {
			return "redirect:/products";
		}
		Product product = new Product(name, code, Long.parseLong(price), description);
		Product inserted = productService.saveProduct(product, categoryIds);
		String productId = Long.toString(inserted.getId());
		return "redirect:/products/show/" + productId;
	}

	/**
	 * Retorna la pagina de editar product
	 */
	@RequestMapping("/products/edit/{id}")
	public String edit(@PathVariable Long id, HttpSession session, Model model) {
		Integer currentRole = (Integer) session.getAttribute("currentRole");
		if (currentRole <= 1) {
			return "redirect:/products";
		}
		Optional<Product> productExists = productService.findById(id);
		if (productExists != null) {
			Product product = productExists.get();
			model.addAttribute("name", product.getName());
			model.addAttribute("code", product.getCode());
			model.addAttribute("price", product.getPrice());
			model.addAttribute("categories", categoryService.allCategorys());
			model.addAttribute("productCategories", product.getCategories());
			return "product/edit.jsp";
		}
		return "product/list.jsp";
	}

	/**
	 * Edita producto, devuelve la vista de producto
	 */
	@RequestMapping("/products/update/{id}")
	public String update(@ModelAttribute("product") Product prod, @PathVariable Long id,
			@RequestParam("categories") String[] categoryIds, HttpSession session, Model model) {
		Integer currentRole = (Integer) session.getAttribute("currentRole");
		if (currentRole <= 1) {
			return "redirect:/products";
		}
		Optional<Product> productExists = productService.findById(id);
		if (productExists != null) {
			productService.saveProduct(prod, categoryIds);
			String productId = Long.toString(id);
			return "redirect:/products/show/" + productId;
		}

		return "redirect:/products/edit/" + id;
	}

	/**
	 * Retorna la pagina de mostrar producto
	 */
	@RequestMapping("/products/show/{id}")
	public String show(@PathVariable Long id, Model model) {
		Optional<Product> productExists = productService.findById(id);
		if (productExists != null) {
			Product product = productExists.get();
			model.addAttribute("name", product.getName());
			model.addAttribute("code", product.getCode());
			model.addAttribute("price", product.getPrice());
			model.addAttribute("description", product.getDescription());
			model.addAttribute("productCategories", product.getCategories());
			return "product/show.jsp";
		}
		return "redirect:/products";
	}

	/**
	 * Elimina producto y vuelve a la lista
	 */
	@RequestMapping("/products/delete/{id}")
	public String del(@PathVariable Long id, HttpSession session, Model model) {
		Integer currentRole = (Integer) session.getAttribute("currentRole");
		if (currentRole <= 1) {
			return "redirect:/products";
		}
		productService.deleteById(id);
		return "redirect:/products";
	}

}
