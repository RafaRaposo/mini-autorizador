package br.com.autorizador.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class TransacaoDTO extends CartaoDTO {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private BigDecimal valor;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
