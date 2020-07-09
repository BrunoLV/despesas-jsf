package br.com.valhala.despesas.infraestrutura.jpa;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.valhala.despesas.aplicacao.repositorios.LancamentoRepository;
import br.com.valhala.despesas.model.entidades.Lancamento;

@Named
@ApplicationScoped
public class LancamentoRepositoryJpa implements LancamentoRepository {

	@PersistenceContext(name = "despesas-pu")
	private EntityManager em;
	
	@Override
	public Lancamento salva(Lancamento lancamento) {
		em.persist(lancamento);
		return lancamento;
	}
	
	@Override
	public void atualiza(Lancamento lancamento) {
		em.merge(lancamento);
	}
	

	@Override
	public void deleta(Lancamento lancamento) {
		em.remove(lancamento);
	}

	@Override
	public List<Lancamento> listaTodos() {
		return em.createQuery("from Lancamento", Lancamento.class).getResultList();
	}

}
