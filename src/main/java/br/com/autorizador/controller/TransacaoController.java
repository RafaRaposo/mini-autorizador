package br.com.autorizador.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.autorizador.dto.TransacaoDTO;
import br.com.autorizador.enums.ErrorEnum;
import br.com.autorizador.service.TransacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Validated
@Api(value = "Transacao")
@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

	@Autowired
	private TransacaoService transacaoService;

	@ApiOperation(value = "Fazer uma transacaoo")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Transacao realizada"),
			@ApiResponse(code = 400, message = "Campo invalido"),
			@ApiResponse(code = 422, message = "Erro na Transacao")
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ErrorEnum realizarTransacao(@RequestBody @Valid TransacaoDTO transacao) {
		return transacaoService.realizarTransacao(transacao);
	}
	
}
