package br.com.valhala.despesas.model.comandos;

import java.io.Serializable;

import br.com.valhala.despesas.model.entidades.Lancamento;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ComandoNovoLancamento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Getter
	private Lancamento lancamento;

}
