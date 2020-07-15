package br.com.valhala.despesas.model.excecoes;

public class LancamentoInexistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public LancamentoInexistenteException(String message) {
		super(message);
	}

}
