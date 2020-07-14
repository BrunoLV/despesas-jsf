package br.com.valhala.despesas.web.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class IndexController implements Serializable {

	private static final long serialVersionUID = 1L;

	public void redirecionaListagemLancamentos() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/ui/lancamentos/listagem.xhtml");
	}

}
