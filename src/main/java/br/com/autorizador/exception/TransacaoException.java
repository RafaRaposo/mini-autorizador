package br.com.autorizador.exception;

import br.com.autorizador.enums.ErrorEnum;

public class TransacaoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private ErrorEnum error;
	
	public TransacaoException(ErrorEnum e) {
		super(e.name());
		this.error = e;
	}
	
	public ErrorEnum getError() {
		return this.error;
	}
	
}
