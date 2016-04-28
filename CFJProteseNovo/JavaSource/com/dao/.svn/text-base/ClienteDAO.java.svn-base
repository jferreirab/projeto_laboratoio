package com.dao;

import com.model.Cliente;


public class ClienteDAO extends GenericDAO<Cliente> {
	private static final long serialVersionUID = 1L;

    public ClienteDAO() {
		super(Cliente.class);
	}

	public void delete(Cliente cliente) {
		super.delete(cliente.getId(), Cliente.class);
	}
}
