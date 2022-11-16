package br.com.autorizador.exception;

import br.com.autorizador.enums.ErrorEnum;

public class CartaoExpiradoException extends TransacaoException{

	private static final long serialVersionUID = 1L;

	public CartaoExpiradoException(ErrorEnum e) {
		super(e);
	}
	
}
