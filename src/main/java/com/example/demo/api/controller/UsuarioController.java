package com.example.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.dto.UsuarioDTO;
import com.example.demo.exception.RegraDeNegocioException;
import com.example.demo.exception.ErroAutenticacaoException;
import com.example.demo.model.entity.Usuario;
import com.example.demo.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;

	@PostMapping
	public ResponseEntity<Object> salvar(@RequestBody UsuarioDTO dto) {
		
		Usuario usuario = Usuario.builder()
				.email(dto.getEmail())
				.nome(dto.getNome())
				.senha(dto.getSenha())
				.build();
		
		try {
			Usuario usuarioSalvo = service.salvarUsuario(usuario);
			return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
		} catch (RegraDeNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
//			return new ResponseEntity<>(new ApiMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity<Object> autenticar(@RequestBody UsuarioDTO dto){
		
		try {
			Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);

		} catch (ErroAutenticacaoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
