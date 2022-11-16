package br.com.autorizador.exception;

import br.com.autorizador.enums.ErrorEnum;

public class CartaoInexistenteException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CartaoInexistenteException(ErrorEnum e) {
		super(e.name());
	}
	
}
