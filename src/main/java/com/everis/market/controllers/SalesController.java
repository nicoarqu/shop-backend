package com.everis.market.controllers;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.market.models.Product;
import com.everis.market.models.Sale;
import com.everis.market.services.SaleService;

@Controller
public class SalesController {

	// servicio
	@Autowired
	SaleService saleService;

	/**
	 * Retorna todos los usuarios
	 */
	@RequestMapping("/sales")
	public String index(HttpSession session, Model model) {
		Integer currentRole = (Integer) session.getAttribute("currentRole");
		if (currentRole <= 1) {
			return "redirect:/products";
		}
		List<Sale> allSales = saleService.allSales();
		model.addAttribute("allSales", allSales);
		return "sale/list.jsp";
	}

	/**
	 * Retorna la pagina de registro
	 */
	@RequestMapping("/sales/new")
	public String newSale(HttpSession session, Model model) {
		Integer currentRole = (Integer) session.getAttribute("currentRole");
		if (currentRole <= 1) {
			return "redirect:/products";
		}
		return "sale/new.jsp";
	}

	/**
	 * Retorna la pagina de registro, le agrega el usuario creador
	 */
	@RequestMapping("/sales/create")
	public String create(HttpSession session, Model model) {
		Integer currentRole = (Integer) session.getAttribute("currentRole");
		if (currentRole <= 1) {
			return "redirect:/products";
		}
		Long userId = (long) 1; // cambiar esto
		Sale inserted = saleService.createSale(userId);
		String saleId = Long.toString(inserted.getId());
		return "redirect:/sales/show/" + saleId;
	}

	/**
	 * Retorna la pagina de registro
	 */
	@RequestMapping("/sales/edit/{id}")
	public String edit(@PathVariable Long id, HttpSession session, Model model) {
		Integer currentRole = (Integer) session.getAttribute("currentRole");
		if (currentRole <= 1) {
			return "redirect:/products";
		}
		Optional<Sale> saleExists = saleService.findById(id);
		if (saleExists != null) {
			Sale sale = saleExists.get();
			model.addAttribute("buyer", sale.getBuyer());
			return "sale/edit.jsp";
		}
		return "sale/list.jsp";
	}

	/**
	 * Retorna la pagina de editar usuario, devuelve el usuario con los nuevos
	 * cambios si tiene exito
	 */
	@RequestMapping("/sales/update/{id}")
	public String update(@RequestParam(value = "buyer") String buyer, @RequestParam(value = "total") String total,
			@PathVariable Long id, HttpSession session, Model model) {
		Integer currentRole = (Integer) session.getAttribute("currentRole");
		if (currentRole <= 1) {
			return "redirect:/products";
		}
		Optional<Sale> saleExists = saleService.findById(id);
		if (saleExists != null) {
			Sale sale = saleExists.get();

			saleService.saveSale(sale);
			String saleId = Long.toString(id);
			return "redirect:/sales/show/" + saleId;

		}

		return "redirect:/sales/edit/" + id;
	}

	/**
	 * Retorna la pagina de mostrar venta
	 */
	@RequestMapping("/sales/show/{id}")
	public String show(@PathVariable Long id, Model model, HttpSession session) {
		Optional<Sale> saleExists = saleService.findById(id);
		if (saleExists != null) {
			Sale sale = saleExists.get();
			model.addAttribute("buyer", sale.getBuyer().getName());
			model.addAttribute("createdAt", sale.getCreatedAt());
			model.addAttribute("cartProducts", sale.getProducts());
			// guardo venta actual
			session.setAttribute("currentSale", sale.getId());
			int total = 0;
			for (Product p : sale.getProducts()) {
				total += p.getPrice();
			}
			model.addAttribute("total", total);
			return "sale/show.jsp";
		}
		return "redirect:/sales";
	}

	/**
	 * Añade el producto a la venta
	 */
	@RequestMapping("/sales/product/{productId}")
	public String addProduct(@PathVariable Long productId, HttpSession session, Model model) {
		Long saleId = (Long) session.getAttribute("currentSale");
		saleService.addProductToSale(productId, saleId);
		return "redirect:/sales/show/" + Long.toString(saleId);
	}

}
