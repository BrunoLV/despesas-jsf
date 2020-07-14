package br.com.valhala.despesas.aplicacao.interno;

import java.io.Serializable;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import br.com.valhala.despesas.aplicacao.repositorios.LancamentoRepository;
import br.com.valhala.despesas.model.comandos.ComandoDeletaLancamento;
import br.com.valhala.despesas.model.comandos.ComandoEditaLancamento;
import br.com.valhala.despesas.model.comandos.ComandoNovoLancamento;
import br.com.valhala.despesas.model.entidades.Lancamento;

@Named
@ApplicationScoped
public class LancamentoCommandService implements Serializable {
	
	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LancamentoRepository repository;
	
	@Transactional(value = TxType.REQUIRED)
	public void deleta(ComandoDeletaLancamento comando) {
		repository.deleta(comando.getId());
	}

	@Transactional(value = TxType.REQUIRED)
	public void edita(ComandoEditaLancamento comando) {
		
		Lancamento lancamentoBanco = repository.buscaPorId(comando.getId());
		if (lancamentoBanco == null) {
			throw new RuntimeException("Lançamento não encontrado.");
		}
		
		Lancamento lancamentoEdicao = lancamentoBanco.sobrepoe(comando.getLancamento());
		
		Set<ConstraintViolation<Lancamento>> erros = validator.validate(lancamentoEdicao);
		
		if (erros != null && !erros.isEmpty()) {
			throw new RuntimeException("Dados inválidos");
		}
		
		repository.atualiza(lancamentoEdicao);
	}

	@Transactional(value = TxType.REQUIRED)
	public void salva(ComandoNovoLancamento comando) {
		
		Lancamento lancamentoNovo = Lancamento.copia(comando.getLancamento());
		
		Set<ConstraintViolation<Lancamento>> erros = validator.validate(lancamentoNovo);
		if (erros != null && !erros.isEmpty()) {
			throw new RuntimeException("Dados inválidos");
		}
		
		repository.salva(lancamentoNovo);
		
	}

}
