package br.com.autorizador.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.autorizador.dto.CartaoDTO;
import br.com.autorizador.entity.Cartao;
import br.com.autorizador.enums.ErrorEnum;
import br.com.autorizador.exception.CartaoExistenteException;
import br.com.autorizador.repository.CartaoRepository;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository cartaoRepository;

	public CartaoDTO criarCartao(CartaoDTO cartaoDTO) {

		Cartao cartao = new Cartao(cartaoDTO.getNumeroCartao(), cartaoDTO.getSenha());
		cartao.setDataCriacao(LocalDateTime.now());
		cartao.setDataValidade(LocalDate.now().plusYears(5));
		cartao.setSaldo(new BigDecimal("500"));

		Object teste = existePorNumeroCartao(cartaoDTO.getNumeroCartao()) ? handleCartaoExistente(cartaoDTO)
				: new CartaoDTO(cartaoRepository.save(cartao));
		
		return (CartaoDTO) teste;
	}
	
	private boolean existePorNumeroCartao(Long numeroCartao) {
		return cartaoRepository.existsByNumeroCartao(numeroCartao);
	}

	private Object handleCartaoExistente(CartaoDTO cartaoDTO) {
		throw new CartaoExistenteException(ErrorEnum.CARTAO_EXISTENTE, cartaoDTO);
	}
}
