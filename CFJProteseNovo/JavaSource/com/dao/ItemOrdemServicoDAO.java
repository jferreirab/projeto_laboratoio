package com.dao;

import com.model.ItemOrdemServico;


public class ItemOrdemServicoDAO extends GenericDAO<ItemOrdemServico> {

	private static final long serialVersionUID = 1L;

    public ItemOrdemServicoDAO() 
    {
		super(ItemOrdemServico.class);
	}

	public void delete(ItemOrdemServico itemOrdemServico) {
		super.delete(itemOrdemServico.getId(), ItemOrdemServico.class);
	}
}
