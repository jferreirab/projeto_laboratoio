package com.facade;

import java.io.Serializable;
import java.util.List;

import com.dao.ClienteDAO;

import com.model.Cliente;

public class ClienteFacade implements Serializable {
   
	private static final long serialVersionUID = 1L;
	
	private ClienteDAO clienteDAO = new ClienteDAO();

	public void createCliente(Cliente cliente) {
		clienteDAO.beginTransaction();
		clienteDAO.save(cliente);
		clienteDAO.commitAndCloseTransaction();
	}

	public void updateCliente(Cliente cliente) {
		clienteDAO.beginTransaction();
		Cliente persistedCliente = clienteDAO.find(cliente.getId());
		persistedCliente.setEndereco(cliente.getEndereco());
		persistedCliente.setName(cliente.getName());
		clienteDAO.update(persistedCliente);
		clienteDAO.commitAndCloseTransaction();
	}

	public Cliente findCliente(int clienteId) {
		clienteDAO.beginTransaction();
		Cliente cliente = clienteDAO.find(clienteId);
		clienteDAO.closeTransaction();
		return cliente;
	}

	public List<Cliente> listAll() {
		clienteDAO.beginTransaction();
		List<Cliente> result = clienteDAO.findAll();
		clienteDAO.closeTransaction();
		return result;
	}

	public void deleteCliente(Cliente cliente) {
		clienteDAO.beginTransaction();
		Cliente persistedCliente = clienteDAO.findReferenceOnly(cliente.getId());
		clienteDAO.delete(persistedCliente);
		clienteDAO.commitAndCloseTransaction();
	}
}
