package com.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.facade.ServicoFacade;
import com.model.Servico;
import com.model.User;

@ViewScoped
@ManagedBean
public class ServicoMB extends AbstractMB implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private Servico servico;
	private List<Servico> servicos;
	private ServicoFacade servicoFacade;
	private User user;
	
	public ServicoFacade getServicoFacade() {
		if (servicoFacade == null) {
			servicoFacade = new ServicoFacade();
		}

		return servicoFacade;
	}

	public Servico getServico() {
		if (servico == null) {
			servico = new Servico();
		}

		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public void createServico() {
		try {
			user =  (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"); 
			if(user != null){
				servico.setIdUser(user.getId());
			}
			servico.setDtCadastro(new Date());
			getServicoFacade().createServico(servico);
			closeDialog();
			displayInfoMessageToUser("Dados Gravados com Sucesso!!");
			loadServicos();
			resetServico();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Erro ao Gravar Dados. Favor verifica!");
			e.printStackTrace();
		}
	}
	
	public void updateServico() {
		try {
			user =  (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"); 
			if(user != null){
				servico.setIdUserAlteracao(user.getId());
			}
			getServicoFacade().updateServico(servico);
			closeDialog();
			displayInfoMessageToUser("Dados Alterados com sucesso!!!");
			loadServicos();
			resetServico();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Erro ao Alterar Dados.Favor verificar!!");
			e.printStackTrace();
		}
	}
	
	public void deleteServico() {
		try {
			getServicoFacade().deleteServico(servico);
			closeDialog();
			displayInfoMessageToUser("Deleted With Sucess");
			loadServicos();
			resetServico();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public List<Servico> getAllServicos() {
		if (servicos == null) {
			loadServicos();
		}

		return servicos;
	}

	private void loadServicos() {
		servicos = getServicoFacade().listAll();
	}

	public void resetServico() {
		servico = new Servico();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
