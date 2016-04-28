package com.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.facade.ItemOrdemServicoFacade;
import com.model.ItemOrdemServico;


@FacesConverter(forClass = com.model.ItemOrdemServico.class)
public class ItemOrdemServicoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		ItemOrdemServicoFacade itemOrdemServicoFacade = new ItemOrdemServicoFacade();
		int itemOrdemServicoId;

		try {
			itemOrdemServicoId = Integer.parseInt(arg2);
		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a descrição de uma ordem de Serviço "," Digite a descrição de uma ordem de serviço"));
		}

		return itemOrdemServicoFacade.findItemOrdemServico(itemOrdemServicoId);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) {
			return "";
		}
		ItemOrdemServico itemOrdemServico = (ItemOrdemServico) arg2;
		return String.valueOf(itemOrdemServico.getId());
	}

}
