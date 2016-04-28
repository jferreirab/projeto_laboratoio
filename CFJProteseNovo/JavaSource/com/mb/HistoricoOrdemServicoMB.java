package com.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.facade.HistoricoOrdemServicoFacade;
import com.facade.OrdemServicoFacade;
import com.model.HistoricoOrdemServico;
import com.model.OrdemServico;
import com.model.Situacao;

@ViewScoped
@ManagedBean
public class HistoricoOrdemServicoMB extends AbstractMB implements Serializable {
     
	private static final long serialVersionUID = 1L;

	private HistoricoOrdemServico historicoOrdemServico;
	private List<HistoricoOrdemServico> historicoOrdemServicos;
	private HistoricoOrdemServicoFacade historicoOrdemServicoFacade;
	private OrdemServicoFacade ordemServicoFacade;
	private OrdemServico ordemServico;
	private List<OrdemServico> allOrdemServicos;
	private Situacao situacao;

	public HistoricoOrdemServicoFacade getHistoricoOrdemServicoFacade() {
		if (historicoOrdemServicoFacade == null) {
			historicoOrdemServicoFacade = new HistoricoOrdemServicoFacade();
		}

		return historicoOrdemServicoFacade;
	}

	public HistoricoOrdemServico getHistoricoOrdemServico() {
		if (historicoOrdemServico == null) {
			historicoOrdemServico = new HistoricoOrdemServico();
		}

		return historicoOrdemServico;
	}

	public void setHistoricoOrdemServico(HistoricoOrdemServico historicoOrdemServico) {
		this.historicoOrdemServico = historicoOrdemServico;
	}

	public void createHistoricoOrdemServico() {
		try {
			if (situacao != null){
				historicoOrdemServico.setSituacao(situacao);
			}
			if(ordemServico != null){
				//historicoOrdemServico.setIdOS(ordemServico.getId());
				historicoOrdemServico.setOrdemServico(ordemServico);
			}
			getHistoricoOrdemServicoFacade().createHistoricoOrdemServico(historicoOrdemServico);
			closeDialog();
			displayInfoMessageToUser("Created With Sucess");
			loadHistoricoOrdemServicos();
			resetHistoricoOrdemServico();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}
	
	public void updateHistoricoOrdemServico() {
		try {
			if (situacao != null){
				historicoOrdemServico.setSituacao(situacao);
			}
			getHistoricoOrdemServicoFacade().updateHistoricoOrdemServico(historicoOrdemServico);
			closeDialog();
			displayInfoMessageToUser("Updated With Sucess");
			loadHistoricoOrdemServicos();
			resetHistoricoOrdemServico();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}
	
	public void deleteHistoricoOrdemServico() {
		try {
			getHistoricoOrdemServicoFacade().deleteHistoricoOrdemServico(historicoOrdemServico);
			closeDialog();
			displayInfoMessageToUser("Deleted With Sucess");
			loadHistoricoOrdemServicos();
			resetHistoricoOrdemServico();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public List<HistoricoOrdemServico> getAllHistoricoOrdemServicos() {
		if (historicoOrdemServicos == null) {
			loadHistoricoOrdemServicos();
		}

		return historicoOrdemServicos;
	}

	private void loadHistoricoOrdemServicos() {
		historicoOrdemServicos = getHistoricoOrdemServicoFacade().listAll();
	}

	public void resetHistoricoOrdemServico() {
		historicoOrdemServico = new HistoricoOrdemServico();
		ordemServico      = new OrdemServico();
		situacao     = null;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	
	public List<OrdemServico> complete(String name) {
		List<OrdemServico> queryResult = new ArrayList<OrdemServico>();

		if (allOrdemServicos == null) {
			ordemServicoFacade = new OrdemServicoFacade();
			allOrdemServicos = ordemServicoFacade.listAll();
		}

		//allOrdemServicos.remove(historicoOrdemServico.getIdOS());
		allOrdemServicos.remove(historicoOrdemServico.getOrdemServico().getId());

		for (OrdemServico ordemServico : allOrdemServicos) {
			if (ordemServico.getDsDescricao().toLowerCase().contains(name.toLowerCase())) {
				queryResult.add(ordemServico);
			}
		}

		return queryResult;
	}
	

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	
	public List<Situacao> completeSituacao(String name) {
		List<Situacao> queryResult = new ArrayList<Situacao>();

		/*if (allClientes == null) {
			clienteFacade = new ClienteFacade();
			allClientes = clienteFacade.listAll();
		}

		allClientes.remove(ordemServico.getCliente());
        */
		for (Situacao situacao : Situacao.values()) {
			
				queryResult.add(situacao);
			
		}

		return queryResult;
	}
}
