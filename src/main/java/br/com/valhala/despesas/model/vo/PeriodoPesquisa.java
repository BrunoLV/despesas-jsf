package br.com.valhala.despesas.model.vo;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class PeriodoPesquisa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private LocalDate inicioPeriodo;
	private LocalDate fimPeriodo;

}
