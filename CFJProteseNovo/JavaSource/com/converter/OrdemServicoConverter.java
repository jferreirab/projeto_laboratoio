package com.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.facade.OrdemServicoFacade;
import com.model.OrdemServico;

@FacesConverter(forClass = com.model.OrdemServico.class)
public class OrdemServicoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		OrdemServicoFacade ordemServicoFacade = new OrdemServicoFacade();
		int ordemServicoId;

		try {
			ordemServicoId = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a descrição de uma ordem de Serviço "," Digite a descrição de uma ordem de serviço"));
		}

		return ordemServicoFacade.findOrdemServico(ordemServicoId);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		OrdemServico ordemServico = (OrdemServico) arg2;
		return String.valueOf(ordemServico.getId());
	}

}
