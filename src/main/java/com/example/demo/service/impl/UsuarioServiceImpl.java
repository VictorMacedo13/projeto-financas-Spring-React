package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.EmailJaExisteException;
import com.example.demo.model.entity.Usuario;
import com.example.demo.model.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario autenticae(String email, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario salvarUsuario(Usuario usuario0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validarEmail(String Email) {
	
		boolean exists = usuarioRepository.existsByEmail(Email);
		
		if(exists) {
			throw new EmailJaExisteException("Já existe um usuário cadastrado com este email");
		}
		
	}

}
