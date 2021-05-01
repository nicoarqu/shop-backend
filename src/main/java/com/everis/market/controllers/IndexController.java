package com.everis.market.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	/**
	 * Retorna la pagina principal
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String inicio(Model model) {
		return "index.jsp";
	}

}
