package br.com.valhala.despesas.model.excecoes;

import java.util.Collection;

import lombok.Getter;

public class ValidacaoDadosException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Getter
	private Collection<String> erros;
	
	public ValidacaoDadosException(String message, Collection<String> erros) {
		super(message);
		this.erros = erros;
	}

}
