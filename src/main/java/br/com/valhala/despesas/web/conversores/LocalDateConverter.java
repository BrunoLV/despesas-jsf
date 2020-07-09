package br.com.valhala.despesas.web.conversores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "localDateConverter")
public class LocalDateConverter implements Converter<LocalDate> {

	private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Override
	public LocalDate getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.isEmpty()) {
			return LocalDate.parse(value, DTF);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, LocalDate value) {
		if (value != null) {
			return value.format(DTF);
		}
		return null;
	}

}
