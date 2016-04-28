package com.dao;


import com.model.Servico;

public class ServicoDAO extends GenericDAO<Servico> {
	
	private static final long serialVersionUID = 1L;

    public ServicoDAO() {
		super(Servico.class);
	}

	public void delete(Servico servico) {
		super.delete(servico.getId(), Servico.class);
	}

}
