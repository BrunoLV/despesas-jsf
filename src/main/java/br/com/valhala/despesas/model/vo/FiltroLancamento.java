package br.com.valhala.despesas.model.vo;

import java.io.Serializable;

import br.com.valhala.despesas.model.enumerados.TipoLancamento;
import lombok.Data;

@Data
public class FiltroLancamento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private PeriodoPesquisa periodo = new PeriodoPesquisa();
	private String descricao;
	private TipoLancamento tipo;

}
