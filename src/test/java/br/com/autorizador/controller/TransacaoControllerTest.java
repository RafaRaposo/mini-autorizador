package br.com.autorizador.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.autorizador.dto.TransacaoDTO;
import br.com.autorizador.service.TransacaoService;

@WebMvcTest(TransacaoController.class)
public class TransacaoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private TransacaoService transacaoService;

	@Test
	public void realizarTransacao() throws Exception {
		TransacaoDTO transacao = new TransacaoDTO();
		transacao.setNumeroCartao(1234567l);
		transacao.setSenha(1234);
		transacao.setValor(new BigDecimal("10"));

		mockMvc.perform(post("/transacoes")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(transacao)))
		.andExpect(status().isCreated());
	}
	
	@Test
	public void validarTransacaoSemNumero() throws Exception {
		TransacaoDTO transacao = new TransacaoDTO();
		transacao.setSenha(1234);
		transacao.setValor(new BigDecimal("10"));

		mockMvc.perform(post("/transacoes")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(transacao)))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void validarTransacaoSemSenha() throws Exception {
		TransacaoDTO transacao = new TransacaoDTO();
		transacao.setNumeroCartao(1234567l);
		transacao.setValor(new BigDecimal("10"));

		mockMvc.perform(post("/transacoes")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(transacao)))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void validarTransacaoSemValor() throws Exception {
		TransacaoDTO transacao = new TransacaoDTO();
		transacao.setNumeroCartao(1234567l);
		transacao.setSenha(1234);

		mockMvc.perform(post("/transacoes")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(transacao)))
		.andExpect(status().isBadRequest());
	}

}
