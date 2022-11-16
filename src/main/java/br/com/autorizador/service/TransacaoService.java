package br.com.autorizador.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.autorizador.dto.TransacaoDTO;
import br.com.autorizador.entity.Cartao;
import br.com.autorizador.enums.ErrorEnum;
import br.com.autorizador.exception.HandleException;
import br.com.autorizador.repository.CartaoRepository;

@Service
public class TransacaoService {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private HandleException handleException;

	@Transactional
	public ErrorEnum realizarTransacao(TransacaoDTO transacao) {
		Cartao cartao = cartaoRepository.findByNumeroCartao(transacao.getNumeroCartao());
		return cartao == null ? (ErrorEnum) handleException.retornaException(ErrorEnum.TRANSACAO_CARTAO_INEXISTENTE, null)
				: validarSenha(cartao, transacao);
	}

	private ErrorEnum validarSenha(Cartao cartao, TransacaoDTO transacao) {
		return !cartao.getSenha().equals(transacao.getSenha())
				? (ErrorEnum) handleException.retornaException(ErrorEnum.SENHA_INVALIDA, null)
				: verificarValidadeCartao(cartao, transacao);
	}

	private ErrorEnum verificarValidadeCartao(Cartao cartao, TransacaoDTO transacao) {
		return LocalDate.now().isAfter(cartao.getDataValidade())
				? (ErrorEnum) handleException.retornaException(ErrorEnum.CARTAO_EXPIRADO, null)
				: validarSaldo(cartao, transacao);
	}

	private ErrorEnum validarSaldo(Cartao cartao, TransacaoDTO transacao) {
		return cartao.getSaldo().compareTo(transacao.getValor()) == -1
				? (ErrorEnum) handleException.retornaException(ErrorEnum.SALDO_INSUFICIENTE, null)
				: finalizarTransacao(cartao, transacao);
	}

	private ErrorEnum finalizarTransacao(Cartao cartao, TransacaoDTO transacao) {
		cartao.setSaldo(cartao.getSaldo().subtract(transacao.getValor()));
		cartaoRepository.save(cartao);
		return ErrorEnum.OK;
	}

}
