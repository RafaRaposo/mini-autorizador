package br.com.autorizador.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.autorizador.dto.CartaoDTO;
import br.com.autorizador.service.CartaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Validated
@EnableSwagger2
@Api(value = "Cartao")
@RestController
@RequestMapping("/cartoes")
public class CartaoController {

	@Autowired
	private CartaoService cartaoService;

	@ApiOperation(value = "Cria um cartão")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Cartao criado!"),
			@ApiResponse(code = 400, message = "Campo invalido"),
			@ApiResponse(code = 422, message = "Cartao existente!")
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CartaoDTO criar(@RequestBody @Valid CartaoDTO cartao) {
		return cartaoService.criarCartao(cartao);
	}
	
	@ApiOperation(value = "Verifica o saldo do cartão")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Campo invalido"),
			@ApiResponse(code = 404, message = "Cartao inexistente!")
	})
	@GetMapping(value = "/{numeroCartao}")
	@ResponseStatus(HttpStatus.OK)
	public BigDecimal obterSaldo(@PathVariable Long numeroCartao) {
		return cartaoService.obterSaldo(numeroCartao);
	}

}
