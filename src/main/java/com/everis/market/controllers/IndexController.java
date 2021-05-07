package com.everis.market.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	/**
	 * Retorna la pagina principal
	 */
	@RequestMapping("/")
	public String inicio(Model model) {
		return "index.jsp";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session, Model model) {
		session.setAttribute("currentRole", 0);
		session.removeAttribute("currentUserId");
		session.removeAttribute("currentSale");
		return "index.jsp";
	}

}
