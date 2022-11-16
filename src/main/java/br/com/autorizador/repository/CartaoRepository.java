package br.com.autorizador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.autorizador.entity.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
	
	public boolean existsByNumeroCartao(Long numeroCartao);
		
}
