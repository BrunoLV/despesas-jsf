package br.com.valhala.despesas.model.comandos;

import java.io.Serializable;

import br.com.valhala.despesas.model.entidades.Lancamento;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ComandoEditaLancamento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Getter
	private Long id;
	
	@Getter
	private Lancamento lancamento;

}
