package com.dao;

import com.model.OrdemServico;

public class OrdemServicoDAO extends GenericDAO<OrdemServico> {
	private static final long serialVersionUID = 1L;

    public OrdemServicoDAO() 
    {
		super(OrdemServico.class);
	}

	public void delete(OrdemServico ordemServico) {
		super.delete(ordemServico.getId(), OrdemServico.class);
	}
}
