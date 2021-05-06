package com.everis.market.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String index(Model model) {
		List<Sale> allSales = saleService.allSales();
		model.addAttribute("allSales", allSales);
		return "sale/list.jsp";
	}

	/**
	 * Retorna la pagina de registro
	 */
	@RequestMapping("/sales/new")
	public String newSale(Model model) {
		return "sale/new.jsp";
	}

	/**
	 * Retorna la pagina de registro, le agrega el usuario creador
	 */
	@RequestMapping("/sales/create")
	public String create(Model model) {
		Long userId = (long) 1; // cambiar esto
		Sale inserted = saleService.createSale(userId);
		String saleId = Long.toString(inserted.getId());
		return "redirect:/sales/show/" + saleId;
	}

	/**
	 * Retorna la pagina de registro
	 */
	@RequestMapping("/sales/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
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
			@PathVariable Long id, Model model) {
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
	public String show(@PathVariable Long id, Model model) {
		Optional<Sale> saleExists = saleService.findById(id);
		if (saleExists != null) {
			Sale sale = saleExists.get();
			model.addAttribute("buyer", sale.getBuyer().getName());
			model.addAttribute("createdAt", sale.getCreatedAt());
			return "sale/show.jsp";
		}
		return "redirect:/sales";
	}

	/**
	 * Añade el producto a la venta
	 */
	@RequestMapping("/sales/{saleId}/product/{productId}")
	public String addProduct(@PathVariable Long productId, @PathVariable Long saleId, Model model) {
		saleService.addProductToSale(productId, saleId);
		return "redirect:/sales/show/" + Long.toString(saleId);
	}

}
