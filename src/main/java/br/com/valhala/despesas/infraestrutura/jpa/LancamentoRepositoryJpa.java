package br.com.valhala.despesas.infraestrutura.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.valhala.despesas.aplicacao.repositorios.LancamentoRepository;
import br.com.valhala.despesas.model.entidades.Lancamento;
import br.com.valhala.despesas.model.vo.FiltroLancamento;

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
	public void deleta(Long idLancamento) {
		Lancamento lancamento = em.find(Lancamento.class, idLancamento);
		em.remove(lancamento);
	}

	@Override
	public List<Lancamento> listaTodos() {
		return em.createQuery("from Lancamento", Lancamento.class).getResultList();
	}

	@Override
	public List<Lancamento> listaFiltrando(FiltroLancamento filtro) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteriaQuery = cb.createQuery(Lancamento.class);
        Root<Lancamento> root = criteriaQuery.from(Lancamento.class);
        
        List<Predicate> predicados = new ArrayList<Predicate>();
        
        if (filtro.getPeriodo().getInicioPeriodo() != null) {
			predicados.add(cb.greaterThanOrEqualTo(root.get("data"), filtro.getPeriodo().getInicioPeriodo()));
		}
        
        if (filtro.getPeriodo().getFimPeriodo() != null) {
			predicados.add(cb.lessThanOrEqualTo(root.get("data"), filtro.getPeriodo().getFimPeriodo()));
		}
        
        if (filtro.getDescricao() != null && !filtro.getDescricao().trim().isEmpty()) {
			predicados.add(cb.like(root.get("descricao"), "%" + filtro.getDescricao() + "%"));
		}
        
        if (filtro.getTipo() != null) {
			predicados.add(cb.equal(root.get("tipo"), filtro.getTipo()));
		}
        
        criteriaQuery.select(root);
        if (!predicados.isEmpty()) {			
        	criteriaQuery.where(cb.and(predicados.toArray(new Predicate[predicados.size()])));
		}
        
        TypedQuery<Lancamento> query = em.createQuery(criteriaQuery);
        return query.getResultList();	
	}

	@Override
	public Lancamento buscaPorId(Long id) {
		return em.find(Lancamento.class, id);
	}

}
