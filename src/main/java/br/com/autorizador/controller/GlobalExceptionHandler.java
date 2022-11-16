package br.com.autorizador.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.autorizador.dto.CartaoDTO;
import br.com.autorizador.enums.ErrorEnum;
import br.com.autorizador.exception.CartaoExistenteException;
import br.com.autorizador.exception.CartaoExpiradoException;
import br.com.autorizador.exception.CartaoInexistenteException;
import br.com.autorizador.exception.SaldoInsuficienteException;
import br.com.autorizador.exception.SenhaInvalidaException;
import br.com.autorizador.exception.TransacaoCartaoInexistenteException;
import br.com.autorizador.exception.TransacaoException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CartaoExistenteException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ResponseEntity<CartaoDTO> handleCartaoExistenteException(CartaoExistenteException exception) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getCartao());
	}

	@ExceptionHandler(CartaoInexistenteException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> handleCartaoInexistenteException(CartaoInexistenteException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@ExceptionHandler({ 
		TransacaoCartaoInexistenteException.class, 
		SaldoInsuficienteException.class,
		SenhaInvalidaException.class, 
		CartaoExpiradoException.class })
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ResponseEntity<ErrorEnum> handleTransacaoException(TransacaoException exception) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getError());
	}

}
