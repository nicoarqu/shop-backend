package com.everis.market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.market.models.User;
import com.everis.market.services.UserService;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

	// servicio
	@Autowired
	UserService userService;

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
	 * Retorna la pagina de registro
	 */
	@RequestMapping("/users/create")
	public String create(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, @RequestParam(value = "address") String address,
			Model model) {
		// crear usuario y retorna id
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		user.setAddress(address);

		User inserted = userService.saveUser(user);
		String userId = Long.toString(inserted.getId());
		return "redirect:/users/show/" + userId;
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

		return "redirect:/users/edit/"+id;
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
			return "user/show.jsp";
		}
		return "redirect:/users";
	}

	/**
	 * Elimina usuario y vuelve a la lista
	 */
	@RequestMapping("/users/delete/{id}")
	public String del(@PathVariable Long id, Model model) {
		userService.deleteById(id);
		return "redirect:/users";
	}
}
