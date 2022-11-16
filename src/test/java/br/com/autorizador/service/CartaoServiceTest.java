package br.com.autorizador.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

import br.com.autorizador.dto.CartaoDTO;
import br.com.autorizador.entity.Cartao;
import br.com.autorizador.exception.CartaoExistenteException;
import br.com.autorizador.exception.CartaoInexistenteException;
import br.com.autorizador.exception.HandleException;
import br.com.autorizador.repository.CartaoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartaoServiceTest {

	@InjectMocks
	private CartaoService cartaoService;

	@Mock
	private CartaoRepository cartaoRepository;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@BeforeEach()
	public void init() {
		ReflectionTestUtils.setField(cartaoService, "handleException", new HandleException());
	}

	@Test
	public void criarCartao() {
		when(cartaoRepository.existsByNumeroCartao(any())).thenReturn(false);
		when(cartaoRepository.save(any())).thenReturn(new Cartao());
		CartaoDTO retorno = cartaoService.criarCartao(new CartaoDTO());

		assertNotNull(retorno);
	}

	@Test
	public void validarCartaoExistente() {
		when(cartaoRepository.existsByNumeroCartao(any())).thenReturn(true);

		assertThrows(CartaoExistenteException.class, () -> cartaoService.criarCartao(new CartaoDTO()));
	}

	@Test
	public void obterSaldo() {
		when(cartaoRepository.findByNumeroCartao(any())).thenReturn(new Cartao());
		
		cartaoService.obterSaldo(1234567l);
	}
	
	@Test
	public void validarSaldoCartaoInexistente() {
		when(cartaoRepository.findByNumeroCartao(any())).thenReturn(null);

		assertThrows(CartaoInexistenteException.class, () -> cartaoService.obterSaldo(1234567l));
	}
}
