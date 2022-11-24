package br.com.autorizador.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Transacao DTO")
public class TransacaoDTO extends CartaoDTO {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "Valor da transacao")
	@NotNull
	private BigDecimal valor;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
