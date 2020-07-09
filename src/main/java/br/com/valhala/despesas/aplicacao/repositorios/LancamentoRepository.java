package br.com.valhala.despesas.aplicacao.repositorios;

import java.util.List;

import br.com.valhala.despesas.model.entidades.Lancamento;

public interface LancamentoRepository {
	
	Lancamento salva(Lancamento lancamento);
	void atualiza(Lancamento lancamento);
	void deleta(Lancamento lancamento);
	List<Lancamento> listaTodos();

}
