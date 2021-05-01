package com.everis.market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.market.models.Sale;
import com.everis.market.services.SaleService;

import java.util.List;
import java.util.Optional;

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
	 * Retorna la pagina de registro
	 */
	@RequestMapping("/sales/create")
	public String create(@RequestParam(value = "buyer") String buyer, @RequestParam(value = "total") String total,
			Model model) {
		// crear usuario y retorna id
		Sale sale = new Sale();
		sale.setBuyer(buyer);
		sale.setTotal(Long.parseLong(total));

		Sale inserted = saleService.saveSale(sale);
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
			model.addAttribute("total", sale.getTotal());
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
			sale.setBuyer(buyer);
			sale.setTotal(Long.parseLong(total));

			saleService.saveSale(sale);
			String saleId = Long.toString(id);
			return "redirect:/sales/show/" + saleId;

		}

		return "redirect:/sales/edit/" + id;
	}

	/**
	 * Retorna la pagina de mostrar usuario
	 */
	@RequestMapping("/sales/show/{id}")
	public String show(@PathVariable Long id, Model model) {
		Optional<Sale> saleExists = saleService.findById(id);
		if (saleExists != null) {
			Sale sale = saleExists.get();
			model.addAttribute("buyer", sale.getBuyer());
			model.addAttribute("createdAt", sale.getCreatedAt());
			model.addAttribute("total", sale.getTotal());
			return "sale/show.jsp";
		}
		return "redirect:/sales";
	}

	/**
	 * Elimina usuario y vuelve a la lista
	 */
	@RequestMapping("/sales/delete/{id}")
	public String del(@PathVariable Long id, Model model) {
		saleService.deleteById(id);
		return "redirect:/sales";
	}
}
