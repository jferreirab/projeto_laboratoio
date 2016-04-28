package com.dao;

import com.model.Dentista;


public class DentistaDAO extends GenericDAO<Dentista> {
	
	private static final long serialVersionUID = 1L;

    public DentistaDAO() {
		super(Dentista.class);
	}

	public void delete(Dentista dentista) {
		super.delete(dentista.getId(), Dentista.class);
	}
}
