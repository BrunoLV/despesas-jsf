package br.com.valhala.despesas.aplicacao.interno;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import br.com.valhala.despesas.aplicacao.repositorios.LancamentoRepository;
import br.com.valhala.despesas.model.entidades.Lancamento;

@Named
@ApplicationScoped
public class LancamentoQueryService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LancamentoRepository lancamentoRepository;
	
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Lancamento> listaTodos() {
		return lancamentoRepository.listaTodos();
	}

}
