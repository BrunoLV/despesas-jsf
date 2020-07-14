package br.com.valhala.despesas.web.controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.valhala.despesas.aplicacao.interno.LancamentoCommandService;
import br.com.valhala.despesas.aplicacao.interno.LancamentoQueryService;
import br.com.valhala.despesas.model.comandos.ComandoDeletaLancamento;
import br.com.valhala.despesas.model.entidades.Lancamento;
import br.com.valhala.despesas.model.enumerados.TipoLancamento;
import br.com.valhala.despesas.model.vo.FiltroLancamento;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class ListagemLancamentoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LancamentoQueryService queryService;
	
	@Inject
	private LancamentoCommandService commandService;
	
	@Getter
	@Setter
	private FiltroLancamento filtro;
	
	@Getter
	@Setter
	private List<Lancamento> lancamentos = Collections.emptyList();
	
	@PostConstruct
	public void inicia() {
		this.filtro = new FiltroLancamento();
		this.lancamentos = queryService.listaTodos();
	}
	
	public TipoLancamento[] getTipos() {
		return TipoLancamento.values();
	}
	
	public void pesquisa() {
		this.lancamentos = queryService.listaFiltrando(filtro);
	}
	
	public void limpa() {
		this.filtro = new FiltroLancamento();
	}
	
	public void deleta(Lancamento lancamento) {
		final ComandoDeletaLancamento comandoDeleta = new ComandoDeletaLancamento(lancamento.getId());
		commandService.deleta(comandoDeleta);
		this.lancamentos.remove(lancamento);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Removido com sucesso"));
	}
	
	public String edita(Lancamento lancamento) {
		return "/ui/lancamentos/cadastro.xhtml?faces-redirect=true&id=" + lancamento.getId();
	}
	
	public String detalha(Lancamento lancamento) {
		return "/ui/lancamentos/detalhe.xhtml?faces-redirect=true&id=" + lancamento.getId();
	}

}
