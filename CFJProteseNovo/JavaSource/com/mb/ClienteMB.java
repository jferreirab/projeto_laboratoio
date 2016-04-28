package com.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.facade.ClienteFacade;
import com.model.Cliente;

@ViewScoped
@ManagedBean
public class ClienteMB extends AbstractMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private List<Cliente> clientes;
	private ClienteFacade clienteFacade;

	public ClienteFacade getClienteFacade() {
		if (clienteFacade == null) {
			clienteFacade = new ClienteFacade();
		}

		return clienteFacade;
	}

	public Cliente getCliente() {
		if (cliente == null) {
			cliente = new Cliente();
		}

		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void createCliente() {
		try {
			getClienteFacade().createCliente(cliente);
			closeDialog();
			displayInfoMessageToUser("Dados Gravados com Sucesso!!");
			loadClientes();
			resetCliente();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Erro ao Gravar Dados. Favor verifica!");
			e.printStackTrace();
		}
	}
	
	public void updateCliente() {
		try {
			getClienteFacade().updateCliente(cliente);
			closeDialog();
			displayInfoMessageToUser("Dados Alterados com sucesso!!!");
			loadClientes();
			resetCliente();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Erro ao Alterar Dados.Favor verificar!!");
			e.printStackTrace();
		}
	}
	
	public void deleteCliente() {
		try {
			getClienteFacade().deleteCliente(cliente);
			closeDialog();
			displayInfoMessageToUser("Deleted With Sucess");
			loadClientes();
			resetCliente();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public List<Cliente> getAllClientes() {
		if (clientes == null) {
			loadClientes();
		}

		return clientes;
	}

	private void loadClientes() {
		clientes = getClienteFacade().listAll();
	}

	public void resetCliente() {
		cliente = new Cliente();
	}
}
