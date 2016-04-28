package com.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.facade.DenteFacade;
import com.model.Dente;

@FacesConverter(forClass = com.model.Dente.class)
public class DenteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		DenteFacade denteFacade = new DenteFacade();
		int denteId;

		try {
			denteId = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome dente e selecione-o (ou use o dropdow) "," Digite o nome dente e selecione-o (ou use o dropdow)"));
		}

		return denteFacade.findDente(denteId);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Dente dente = (Dente) arg2;
		return String.valueOf(dente.getId());
	}

}
