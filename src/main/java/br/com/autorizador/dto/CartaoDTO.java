package br.com.autorizador.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.autorizador.entity.Cartao;

public class CartaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Long numeroCartao;
	
	@NotNull
	private int senha;

	public CartaoDTO() {
		
	}
	
	public CartaoDTO(Cartao cartao) {
		this.numeroCartao = cartao.getNumeroCartao();
		this.senha = cartao.getSenha();
	}
	
	public Long getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(Long numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

}
