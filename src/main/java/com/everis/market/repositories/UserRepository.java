package com.everis.market.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.market.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findAll();

	Optional<User> findById(Long id);

	List<User> findByEmailContaining(String email);
}
