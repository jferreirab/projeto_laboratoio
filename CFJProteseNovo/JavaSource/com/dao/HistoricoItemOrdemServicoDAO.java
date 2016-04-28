package com.dao;

import com.model.HistoricoItemOrdemServico;


public class HistoricoItemOrdemServicoDAO extends
		GenericDAO<HistoricoItemOrdemServico> {

		
	private static final long serialVersionUID = 1L;
   
	public HistoricoItemOrdemServicoDAO() 
    {
		super(HistoricoItemOrdemServico.class);
	}

	public void delete(HistoricoItemOrdemServico historicoItemOrdemServico) {
		super.delete(historicoItemOrdemServico.getId(), HistoricoItemOrdemServico.class);
	}
}
