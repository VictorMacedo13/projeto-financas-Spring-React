package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.EmailJaExisteException;
import com.example.demo.exception.ErroAutenticacaoException;
import com.example.demo.model.entity.Usuario;
import com.example.demo.model.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario autenticar(String email, String senha) {
		
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		
		if(!usuario.isPresent()) {
			throw new ErroAutenticacaoException("Email inválido");
		}
		if(!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacaoException("Senha inválida");
		}
		
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return usuarioRepository.save(usuario);
	}

	@Override
	public void validarEmail(String Email) {
	
		boolean exists = usuarioRepository.existsByEmail(Email);
		
		if(exists) {
			throw new EmailJaExisteException("Já existe um usuário cadastrado com este email");
		}
		
	}

}
