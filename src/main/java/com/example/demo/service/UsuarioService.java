package com.example.demo.service;

import com.example.demo.model.entity.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario0);
	
	void validarEmail(String Email);

}
