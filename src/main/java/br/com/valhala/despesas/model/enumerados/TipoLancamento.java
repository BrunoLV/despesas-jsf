package br.com.valhala.despesas.model.enumerados;

public enum TipoLancamento {

    CREDITO("Crédito"),
    DEBITO("Débito");

    private String descricao;

    TipoLancamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}