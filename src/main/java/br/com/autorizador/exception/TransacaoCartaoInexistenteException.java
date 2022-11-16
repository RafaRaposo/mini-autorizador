package br.com.autorizador.exception;

import br.com.autorizador.enums.ErrorEnum;

public class TransacaoCartaoInexistenteException extends TransacaoException{

	private static final long serialVersionUID = 1L;

	public TransacaoCartaoInexistenteException(ErrorEnum e) {
		super(e);
	}
	
}
