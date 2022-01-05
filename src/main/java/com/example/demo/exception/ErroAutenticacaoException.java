package com.example.demo.exception;

public class ErroAutenticacaoException extends RuntimeException{

	public ErroAutenticacaoException(String mensagem) {
		super(mensagem);
	}
}
