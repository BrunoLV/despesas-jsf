package br.com.valhala.despesas.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.valhala.despesas.aplicacao.interno.LancamentoQueryService;
import br.com.valhala.despesas.model.entidades.Lancamento;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class LancamentoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LancamentoQueryService queryService;
	
	@Getter
	@Setter
	private Lancamento lancamento;
	
	@Getter
	@Setter
	private List<Lancamento> lancamentos;
	
	@PostConstruct
	public void inicia() {
		lancamento = new Lancamento();
		this.lancamentos = queryService.listaTodos();
	}

}
