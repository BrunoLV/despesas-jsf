package br.com.valhala.despesas.aplicacao.interno;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import br.com.valhala.despesas.model.excecoes.LancamentoInexistenteException;
import br.com.valhala.despesas.model.excecoes.ValidacaoDadosException;

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
			throw new LancamentoInexistenteException("Lançamento não encontrado na base de dados.");
		}
		
		Lancamento lancamentoEdicao = lancamentoBanco.sobrepoe(comando.getLancamento());
		
		Set<ConstraintViolation<Lancamento>> erros = validator.validate(lancamentoEdicao);
		if (erros != null && !erros.isEmpty()) {
			List<String> mensagensErro = erros.stream().map(e -> e.getMessage()).collect(Collectors.toList());
			throw new ValidacaoDadosException("Dados inválidos", mensagensErro);
		}
		
		repository.atualiza(lancamentoEdicao);
	}

	@Transactional(value = TxType.REQUIRED)
	public void salva(ComandoNovoLancamento comando) {
		
		Lancamento lancamentoNovo = Lancamento.copia(comando.getLancamento());
		
		Set<ConstraintViolation<Lancamento>> erros = validator.validate(lancamentoNovo);
		if (erros != null && !erros.isEmpty()) {
			List<String> mensagensErro = erros.stream().map(e -> e.getMessage()).collect(Collectors.toList());
			throw new ValidacaoDadosException("Dados inválidos", mensagensErro);
		}
		
		repository.salva(lancamentoNovo);
	}

}
