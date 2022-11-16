package br.com.autorizador.enums;

public enum ErrorEnum {
	
	CARTAO_EXISTENTE("Cartão Existente");
	
	private String erro;
	
	private ErrorEnum(String erro) {
		this.erro = erro;
	}
	
	public String getErro() {
		return erro;
	}
}
