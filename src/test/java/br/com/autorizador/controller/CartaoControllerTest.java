package br.com.autorizador.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.autorizador.dto.CartaoDTO;
import br.com.autorizador.service.CartaoService;

@WebMvcTest(CartaoController.class)
public class CartaoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CartaoService cartaoService;
	
	private static final String URL = "/cartoes";

	@Test
	public void criarCartao() throws Exception {
		CartaoDTO cartao = new CartaoDTO();
		cartao.setNumeroCartao(1234567l);
		cartao.setSenha(1234);

		mockMvc.perform(post(URL)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(cartao)))
		.andExpect(status().isCreated());
	}
	
	@Test
	public void validarCartaoSemNumero() throws Exception {
		CartaoDTO cartao = new CartaoDTO();
		cartao.setSenha(1234);

		mockMvc.perform(post(URL)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(cartao)))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void validarCartaoSemSenha() throws Exception {
		CartaoDTO cartao = new CartaoDTO();
		cartao.setNumeroCartao(1234567l);

		mockMvc.perform(post(URL)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(cartao)))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void obterSaldo() throws Exception {
		mockMvc.perform(get(URL + "/1234567"))
		.andExpect(status().isOk());
	}

}
