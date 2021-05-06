package com.everis.market.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.market.models.Sale;
import com.everis.market.models.User;
import com.everis.market.services.SaleService;
import com.everis.market.services.UserService;

@Controller
public class UsersController {

	// servicio de modelos
	@Autowired
	UserService userService;
	@Autowired
	SaleService saleService;
	/*
	 * config de seguridad
	 * 
	 * @Autowired BCryptPasswordEncoder encoder;
	 */

	/**
	 * Retorna todos los usuarios
	 */
	@RequestMapping("/users")
	public String index(Model model) {
		List<User> allUsers = userService.allUsers();
		model.addAttribute("allUsers", allUsers);
		return "user/list.jsp";
	}

	/**
	 * Retorna la pagina de registro
	 */
	@RequestMapping("/users/new")
	public String newUser(Model model) {
		return "user/new.jsp";
	}

	/**
	 * Registra usuario
	 */
	@RequestMapping("/users/create")
	public String create(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, @RequestParam(value = "address") String address,
			Model model) {
		// crear usuario y retorna id
		User user = new User();
		user.setName(name);
		// contraseña segura
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		user.setPassword(hashed);
		user.setEmail(email);
		user.setAddress(address);

		User inserted = userService.saveUser(user);
		String userId = Long.toString(inserted.getId());
		return "redirect:/users/show/" + userId;
	}

	/**
	 * Retorna la pagina de inicio sesion
	 */
	@RequestMapping("/users/signin")
	public String login(Model model) {
		return "user/signin.jsp";
	}

	/**
	 * Inicia sesion
	 */
	@RequestMapping("/users/login")
	public String create(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password,
			HttpSession session, Model model) {
		User user = userService.findByEmail(email);
		// verifica si existe y guarda id en sesión
		if (BCrypt.checkpw(password, user.getPassword())) {
			System.out.println("ingresado!!");
			session.setAttribute("currentUserId", user.getId());
			session.setAttribute("userLogged", 1);// boolean
			return "redirect:/users/show/" + Long.toString(user.getId());
		}
		session.removeAttribute("userLogged");
		session.removeAttribute("currentUserId");
		model.addAttribute("errorMessage", "Datos erroneos");
		return "redirect:/users/signin";
	}

	/**
	 * Retorna la pagina de registro
	 */
	@RequestMapping("/users/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Optional<User> userExists = userService.findById(id);
		if (userExists != null) {
			User user = userExists.get();
			model.addAttribute("name", user.getName());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("password", user.getPassword());
			model.addAttribute("address", user.getAddress());
			return "user/edit.jsp";
		}
		return "user/list.jsp";
	}

	/**
	 * Retorna la pagina de editar usuario, devuelve el usuario con los nuevos
	 * cambios si tiene exito
	 */
	@RequestMapping("/users/update/{id}")
	public String update(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, @RequestParam(value = "address") String address,
			@PathVariable Long id, Model model) {
		Optional<User> userExists = userService.findById(id);
		if (userExists != null) {
			User user = userExists.get();
			user.setName(name);
			user.setPassword(password);
			user.setEmail(email);
			user.setAddress(address);

			userService.saveUser(user);
			String userId = Long.toString(id);
			return "redirect:/users/show/" + userId;

		}

		return "redirect:/users/edit/" + id;
	}

	/**
	 * Retorna la pagina de mostrar usuario
	 */
	@RequestMapping("/users/show/{id}")
	public String show(@PathVariable Long id, Model model) {
		Optional<User> userExists = userService.findById(id);
		if (userExists != null) {
			User user = userExists.get();
			model.addAttribute("name", user.getName());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("password", user.getPassword());
			model.addAttribute("address", user.getAddress());
			model.addAttribute("userId", id);
			return "user/show.jsp";
		}
		return "redirect:/users";
	}

	/**
	 * Ir a comprar
	 */
	@RequestMapping("/users/buy/{id}")
	public String addSale(@PathVariable Long id, Model model) {
		Optional<User> userExists = userService.findById(id);
		User user = userExists.get();
		Sale sale = new Sale();
		// actualizo los campos correspondientes
		user.addSale(sale);
		sale.setBuyer(user);
		// guardar en ambas tablas
		userService.saveUser(user);
		saleService.saveSale(sale);

		return "redirect:/products";
	}

}
