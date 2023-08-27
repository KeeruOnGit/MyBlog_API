package com.myblogapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myblogapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	Optional<User> findByUsername(String username);
	Optional<User> findByUsernameOrEmail(String username, String email);
	boolean existsByEmail(String email);
	boolean existsByUsername(String username);

}
