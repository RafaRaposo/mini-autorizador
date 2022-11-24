package br.com.autorizador.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.autorizador.entity.Cartao;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Cartao DTO")
public class CartaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "Numero do cartao")
	@NotNull
	private Long numeroCartao;
	
	@ApiModelProperty(value = "Senha do cartao")
	@NotNull
	private Integer senha;

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

	public Integer getSenha() {
		return senha;
	}

	public void setSenha(Integer senha) {
		this.senha = senha;
	}

}
