package com.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.facade.ServicoFacade;
import com.model.Servico;

@FacesConverter(forClass = com.model.Servico.class)
public class ServicoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		ServicoFacade servicoFacade = new ServicoFacade();
		int servicoId;

		try {
			servicoId = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome de um Servico e selecione-o (ou use o dropdow) "," Digite o nome de um Serviço e selecione-o (ou use o dropdow)"));
		}

		return servicoFacade.findServico(servicoId);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Servico servico = (Servico) arg2;
		return String.valueOf(servico.getId());
	}

}
