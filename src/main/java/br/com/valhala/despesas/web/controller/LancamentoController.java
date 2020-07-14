package br.com.valhala.despesas.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.valhala.despesas.aplicacao.interno.LancamentoCommandService;
import br.com.valhala.despesas.aplicacao.interno.LancamentoQueryService;
import br.com.valhala.despesas.model.comandos.ComandoEditaLancamento;
import br.com.valhala.despesas.model.comandos.ComandoNovoLancamento;
import br.com.valhala.despesas.model.entidades.Lancamento;
import br.com.valhala.despesas.model.enumerados.TipoLancamento;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class LancamentoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LancamentoQueryService queryService;
	
	@Inject
	private LancamentoCommandService commandService;
	
	@Getter
	@Setter
	private Lancamento lancamento;
	
	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private List<Lancamento> lancamentos;
	
	@PostConstruct
	public void inicia() {
		lancamento = new Lancamento();
		this.lancamentos = queryService.listaTodos();
	}
	
	public void carregaLancamento() {
		if (this.id != null) {
			lancamento = queryService.buscaPorId(this.id);
		} else {			
			lancamento = new Lancamento();
		}
	}
	
	public TipoLancamento[] getTipos() {
		return TipoLancamento.values();
	}
	
	public void salva() {
		try {
			if (this.id != null) {
				commandService.edita(new ComandoEditaLancamento(id, this.lancamento));
			} else {
				commandService.salva(new ComandoNovoLancamento(this.lancamento));
				this.lancamento = new Lancamento();
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Operação realizada com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}
	
	public void limpa() {
		if (id != null) {
			this.lancamento = queryService.buscaPorId(this.id);
		} else {
			this.lancamento = new Lancamento();
		}
	}

}
