package br.com.autorizador.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.autorizador.dto.TransacaoDTO;
import br.com.autorizador.entity.Cartao;
import br.com.autorizador.enums.ErrorEnum;
import br.com.autorizador.exception.CartaoExpiradoException;
import br.com.autorizador.exception.HandleException;
import br.com.autorizador.exception.SaldoInsuficienteException;
import br.com.autorizador.exception.SenhaInvalidaException;
import br.com.autorizador.exception.TransacaoCartaoInexistenteException;
import br.com.autorizador.repository.CartaoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransacaoServiceTest {

	@InjectMocks
	private TransacaoService transacaoService;

	@Mock
	private CartaoRepository cartaoRepository;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@BeforeEach()
	public void init() {
		ReflectionTestUtils.setField(transacaoService, "handleException", new HandleException());
	}

	@Test
	public void realizarTransacao() {
		Cartao cartao = cartaoPadrao();
		when(cartaoRepository.findByNumeroCartao(any())).thenReturn(cartao);
		when(cartaoRepository.save(any())).thenReturn(cartao);
		ErrorEnum retorno = transacaoService.realizarTransacao(transacaoPadrao());

		assertEquals(ErrorEnum.OK, retorno);
	}
	
	@Test
	public void validarCartaoInexistente() {
		Cartao cartao = cartaoPadrao();
		TransacaoDTO transacao = transacaoPadrao();
		
		when(cartaoRepository.findByNumeroCartao(any())).thenReturn(null);
		when(cartaoRepository.save(any())).thenReturn(cartao);

		assertThrows(TransacaoCartaoInexistenteException.class, () -> transacaoService.realizarTransacao(transacao));
	}

	@Test
	public void validarSenha() {
		Cartao cartao = cartaoPadrao();
		TransacaoDTO transacao = transacaoPadrao();
		transacao.setSenha(54321);
		
		when(cartaoRepository.findByNumeroCartao(any())).thenReturn(cartao);
		when(cartaoRepository.save(any())).thenReturn(cartao);

		assertThrows(SenhaInvalidaException.class, () -> transacaoService.realizarTransacao(transacao));
	}
	
	@Test
	public void validarCartaoExpirado() {
		Cartao cartao = cartaoPadrao();
		cartao.setDataValidade(LocalDate.now().minusDays(1));
		TransacaoDTO transacao = transacaoPadrao();
		
		when(cartaoRepository.findByNumeroCartao(any())).thenReturn(cartao);
		when(cartaoRepository.save(any())).thenReturn(cartao);

		assertThrows(CartaoExpiradoException.class, () -> transacaoService.realizarTransacao(transacao));
	}
	
	@Test
	public void validarSaldoInsuficiente() {
		Cartao cartao = cartaoPadrao();
		cartao.setSaldo(new BigDecimal("9"));
		TransacaoDTO transacao = transacaoPadrao();
		
		when(cartaoRepository.findByNumeroCartao(any())).thenReturn(cartao);
		when(cartaoRepository.save(any())).thenReturn(cartao);

		assertThrows(SaldoInsuficienteException.class, () -> transacaoService.realizarTransacao(transacao));
	}

	private Cartao cartaoPadrao() {
		Cartao cartao = new Cartao(1234567l, 1234);
		cartao.setDataValidade(LocalDate.now().plusDays(1));
		cartao.setSaldo(new BigDecimal("500"));
		
		return cartao;
	}
	
	private TransacaoDTO transacaoPadrao() {
		TransacaoDTO transacao = new TransacaoDTO();
		transacao.setNumeroCartao(1234567l);
		transacao.setSenha(1234);
		transacao.setValor(new BigDecimal("10"));
		
		return transacao;
	}
}
