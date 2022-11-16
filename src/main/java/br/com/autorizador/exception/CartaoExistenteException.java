package br.com.autorizador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.autorizador.dto.CartaoDTO;
import br.com.autorizador.enums.ErrorEnum;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class CartaoExistenteException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private CartaoDTO cartao;

	public CartaoExistenteException(ErrorEnum e, CartaoDTO cartaoDTO) {
		super(e.getErro());
		this.cartao = cartaoDTO;
	}
	
	public CartaoDTO getCartao() {
		return cartao;
	}

}
