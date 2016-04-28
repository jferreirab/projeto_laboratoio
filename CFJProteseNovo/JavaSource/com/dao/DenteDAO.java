package com.dao;

import com.model.Dente;

public class DenteDAO extends GenericDAO<Dente> {

	private static final long serialVersionUID = 1L;

    public DenteDAO() {
		super(Dente.class);
	}

	public void delete(Dente dente) {
		super.delete(dente.getId(), Dente.class);
	}
}
