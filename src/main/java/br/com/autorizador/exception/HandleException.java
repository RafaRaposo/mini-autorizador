package br.com.autorizador.exception;

import org.springframework.stereotype.Component;

import br.com.autorizador.dto.CartaoDTO;
import br.com.autorizador.enums.ErrorEnum;

@Component
public class HandleException {

	public Object retornaException(ErrorEnum error, CartaoDTO cartaoDTO) {
		switch (error) {
			case CARTAO_EXISTENTE:
				throw new CartaoExistenteException(error, cartaoDTO);
			case CARTAO_INEXISTENTE:
				throw new CartaoInexistenteException(error);
			case TRANSACAO_CARTAO_INEXISTENTE:
				throw new TransacaoCartaoInexistenteException(error);
			case SENHA_INVALIDA:
				throw new SenhaInvalidaException(error);
			case CARTAO_EXPIRADO:
				throw new CartaoExpiradoException(error);
			case SALDO_INSUFICIENTE:
				throw new SaldoInsuficienteException(error);
			default:
				return null;
		}
	}

}
