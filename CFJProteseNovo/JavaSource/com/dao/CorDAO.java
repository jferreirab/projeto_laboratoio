package com.dao;

import com.model.Cor;


public class CorDAO extends GenericDAO<Cor> {
	
	private static final long serialVersionUID = 1L;

    public CorDAO() {
		super(Cor.class);
	}

	public void delete(Cor corDente) {
		super.delete(corDente.getId(), Cor.class);
	}
}
