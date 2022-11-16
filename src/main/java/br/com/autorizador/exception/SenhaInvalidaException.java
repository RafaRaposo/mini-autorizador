package br.com.autorizador.exception;

import br.com.autorizador.enums.ErrorEnum;

public class SenhaInvalidaException extends TransacaoException{

	private static final long serialVersionUID = 1L;

	public SenhaInvalidaException(ErrorEnum e) {
		super(e);
	}
	
}
