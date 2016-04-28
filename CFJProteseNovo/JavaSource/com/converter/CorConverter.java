package com.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.facade.CorFacade;
import com.model.Cor;

@FacesConverter(forClass = com.model.Cor.class)
public class CorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		CorFacade corFacade = new CorFacade();
		int corId;

		try {
			corId = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a cor do dente e selecione-o (ou use o dropdow) "," Digite a cor do dente e selecione-o (ou use o dropdow)"));
		}

		return corFacade.findCor(corId);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Cor cor = (Cor) arg2;
		return String.valueOf(cor.getId());
	}

}
