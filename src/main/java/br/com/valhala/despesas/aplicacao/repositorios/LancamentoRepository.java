package br.com.valhala.despesas.aplicacao.repositorios;

import java.util.List;

import br.com.valhala.despesas.model.entidades.Lancamento;
import br.com.valhala.despesas.model.vo.FiltroLancamento;

public interface LancamentoRepository {
	
	Lancamento salva(Lancamento lancamento);
	void atualiza(Lancamento lancamento);
	void deleta(Long idLancamento);
	List<Lancamento> listaTodos();
	List<Lancamento> listaFiltrando(FiltroLancamento filtro);
	Lancamento buscaPorId(Long id);

}
