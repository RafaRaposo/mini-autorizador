package br.com.autorizador.exception;

import br.com.autorizador.dto.CartaoDTO;
import br.com.autorizador.enums.ErrorEnum;

public class CartaoExistenteException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private CartaoDTO cartao;

	public CartaoExistenteException(ErrorEnum e, CartaoDTO cartaoDTO) {
		super(e.name());
		this.cartao = cartaoDTO;
	}
	
	public CartaoDTO getCartao() {
		return cartao;
	}

}
