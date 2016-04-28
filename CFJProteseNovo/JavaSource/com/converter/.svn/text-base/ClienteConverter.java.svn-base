package com.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.facade.ClienteFacade;

import com.model.Cliente;

@FacesConverter(forClass = com.model.Cliente.class)
public class ClienteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		ClienteFacade clienteFacade = new ClienteFacade();
		int clienteId;

		try {
			clienteId = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome de um Cliente e selecione-o (ou use o dropdow) "," Digite o nome de um Cliente e selecione-o (ou use o dropdow)"));
		}

		return clienteFacade.findCliente(clienteId);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		Cliente cliente = (Cliente) arg2;
		return String.valueOf(cliente.getId());
	}

}
