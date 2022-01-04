package com.example.demo.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
		
	boolean existsByEmail(String email);
}
