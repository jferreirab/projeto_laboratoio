package com.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.facade.ClienteFacade;
import com.facade.DentistaFacade;
import com.model.Cliente;
import com.model.Dentista;
import com.model.User;

@ViewScoped
@ManagedBean
public class DentistaMB extends AbstractMB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Dentista dentista;
	private List<Dentista> dentistas;
	private DentistaFacade dentistaFacade;
	private User user;
	private Cliente cliente;
	private List<Cliente> allClientes;
	private ClienteFacade clienteFacade;
	
	public DentistaFacade getDentistaFacade() {
		if (dentistaFacade == null) {
			dentistaFacade = new DentistaFacade();
		}

		return dentistaFacade;
	}
    
	public ClienteFacade getClienteFacade() {
		if (clienteFacade == null) {
			clienteFacade = new ClienteFacade();
		}

		return clienteFacade;
	}
	
	public Dentista getDentista() {
		if (dentista == null) {
			dentista = new Dentista();
		}

		return dentista;
	}

	public void setDentista(Dentista dentista) {
		this.dentista = dentista;
	}

	public void createDentista() {
		try {
			user =  (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"); 
			if(user != null){
				dentista.setIdUser(user.getId());
			}
			dentista.setDtCadastro(new Date());
			getDentistaFacade().createDentista(dentista);
			closeDialog();
			displayInfoMessageToUser("Dados Gravados com Sucesso!!");
			loadDentistas();
			resetDentista();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Erro ao Gravar Dados. Favor verifica!");
			e.printStackTrace();
		}
	}
	
	public void updateDentista() {
		try {
			user =  (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"); 
			if(user != null){
				dentista.setIdUserAlteracao(user.getId());
			}
			getDentistaFacade().updateDentista(dentista);
			closeDialog();
			displayInfoMessageToUser("Dados Alterados com sucesso!!!");
			loadDentistas();
			resetDentista();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Erro ao Alterar Dados.Favor verificar!!");
			e.printStackTrace();
		}
	}
	
	public void deleteDentista() {
		try {
			getDentistaFacade().deleteDentista(dentista);
			closeDialog();
			displayInfoMessageToUser("Deleted With Sucess");
			loadDentistas();
			resetDentista();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public List<Dentista> getAllDentistas() {
		if (dentistas == null) {
			loadDentistas();
		}

		return dentistas;
	}

	private void loadDentistas() {
		dentistas = getDentistaFacade().listAll();
	}

	public void resetDentista() {
		dentista = new Dentista();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Cliente> complete(String name) {
		List<Cliente> queryResult = new ArrayList<Cliente>();

			
		allClientes = getClienteFacade().listAll();
		

		for (Cliente cliente : allClientes) {
			if (cliente.getName().toLowerCase().contains(name.toLowerCase())) {
				queryResult.add(cliente);
			}
		}

		return queryResult;
	}
}
