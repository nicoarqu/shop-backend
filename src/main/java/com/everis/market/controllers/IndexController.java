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
	public String inicio(HttpSession session, Model model) {
		session.setAttribute("userLogged", 0);
		return "index.jsp";
	}

}
