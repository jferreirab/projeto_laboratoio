package com.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.facade.CorFacade;
import com.model.Cor;
import com.model.User;

@ViewScoped
@ManagedBean
public class CorMB extends AbstractMB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Cor cor;
	private List<Cor> cors;
	private CorFacade corFacade;
	private User user;
	
	public CorFacade getCorFacade() {
		if (corFacade == null) {
			corFacade = new CorFacade();
		}

		return corFacade;
	}

	public Cor getCor() {
		if (cor == null) {
			cor = new Cor();
		}

		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public void createCor() {
		try {
			user =  (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"); 
			if(user != null){
				cor.setIdUser(user.getId());
			}
			cor.setDtCadastro(new Date());
			getCorFacade().createCor(cor);
			closeDialog();
			displayInfoMessageToUser("Dados Gravados com Sucesso!!");
			loadCors();
			resetCor();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Erro ao Gravar Dados. Favor verifica!");
			e.printStackTrace();
		}
	}
	
	public void updateCor() {
		try {
			user =  (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"); 
			if(user != null){
				cor.setIdUserAlteracao(user.getId());
			}
			getCorFacade().updateCor(cor);
			closeDialog();
			displayInfoMessageToUser("Dados Alterados com sucesso!!!");
			loadCors();
			resetCor();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Erro ao Alterar Dados.Favor verificar!!");
			e.printStackTrace();
		}
	}
	
	public void deleteCor() {
		try {
			getCorFacade().deleteCor(cor);
			closeDialog();
			displayInfoMessageToUser("Deleted With Sucess");
			loadCors();
			resetCor();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public List<Cor> getAllCors() {
		if (cors == null) {
			loadCors();
		}

		return cors;
	}

	private void loadCors() {
		cors = getCorFacade().listAll();
	}

	public void resetCor() {
		cor = new Cor();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
