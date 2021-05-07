package com.everis.market.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.market.models.User;
import com.everis.market.repositories.RoleRepository;
import com.everis.market.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	// CRUD
	public User saveUser(User user) {
		String role = "USER";
		user.addRole(roleRepository.findByName(role));
		// primer usuario es Admin
		Long cantidad = userRepository.count();
		if (cantidad == 1) {
			role = "ADMIN";
			user.addRole(roleRepository.findByName(role));
		}
		return userRepository.save(user);
	}

	public List<User> allUsers() {
		return userRepository.findAll();
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	/**
	 * Editar usuario por atributos, retorna true si se modifica
	 */
	public boolean editById(Long id, String name, String email, String password, String address) {
		Optional<User> userExists = userRepository.findById(id);
		boolean modified = false;
		if (userExists != null) {
			User user = userExists.get();
			user.setName(name);
			user.setEmail(email);
			user.setAddress(address);
			user.setPassword(password);
			modified = true;
		}
		return modified;
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmailContaining(email).get(0);
	}

}
