package br.com.autorizador.exception;

import br.com.autorizador.enums.ErrorEnum;

public class SaldoInsuficienteException extends TransacaoException{

	private static final long serialVersionUID = 1L;

	public SaldoInsuficienteException(ErrorEnum e) {
		super(e);
	}
	
}
