package com.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.facade.DenteFacade;
import com.model.Dente;
import com.model.User;

@ViewScoped
@ManagedBean
public class DenteMB extends AbstractMB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Dente dente;
	private List<Dente> dentes;
	private DenteFacade denteFacade;
	private User user;
	
	public DenteFacade getDenteFacade() {
		if (denteFacade == null) {
			denteFacade = new DenteFacade();
		}

		return denteFacade;
	}

	public Dente getDente() {
		if (dente == null) {
			dente = new Dente();
		}

		return dente;
	}

	public void setDente(Dente dente) {
		this.dente = dente;
	}

	public void createDente() {
		try {
			user =  (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"); 
			if(user != null){
				dente.setIdUser(user.getId());
			}
			dente.setDtCadastro(new Date());
			getDenteFacade().createDente(dente);
			closeDialog();
			displayInfoMessageToUser("Dados Gravados com Sucesso!!");
			loadDentes();
			resetDente();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Erro ao Gravar Dados. Favor verifica!");
			e.printStackTrace();
		}
	}
	
	public void updateDente() {
		try {
			user =  (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"); 
			if(user != null){
				dente.setIdUserAlteracao(user.getId());
			}
			getDenteFacade().updateDente(dente);
			closeDialog();
			displayInfoMessageToUser("Dados Alterados com sucesso!!!");
			loadDentes();
			resetDente();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Erro ao Alterar Dados.Favor verificar!!");
			e.printStackTrace();
		}
	}
	
	public void deleteDente() {
		try {
			getDenteFacade().deleteDente(dente);
			closeDialog();
			displayInfoMessageToUser("Deleted With Sucess");
			loadDentes();
			resetDente();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public List<Dente> getAllDentes() {
		if (dentes == null) {
			loadDentes();
		}

		return dentes;
	}

	private void loadDentes() {
		dentes = getDenteFacade().listAll();
	}

	public void resetDente() {
		dente = new Dente();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
