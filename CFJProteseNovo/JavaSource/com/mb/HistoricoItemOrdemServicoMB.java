package com.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.facade.HistoricoItemOrdemServicoFacade;
import com.facade.OrdemServicoFacade;
import com.model.HistoricoItemOrdemServico;
import com.model.OrdemServico;


@ViewScoped
@ManagedBean
public class HistoricoItemOrdemServicoMB extends AbstractMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private HistoricoItemOrdemServico historicoItemOrdemServico;
	private List<HistoricoItemOrdemServico> historicoItemOrdemServicos;
	private HistoricoItemOrdemServicoFacade historicoItemOrdemServicoFacade;
	private OrdemServicoFacade ordemServicoFacade;
	private OrdemServico ordemServico;
	private List<OrdemServico> allOrdemServicos;
	//private Situacao situacao;

	public HistoricoItemOrdemServicoFacade getHistoricoItemOrdemServicoFacade() {
		if (historicoItemOrdemServicoFacade == null) {
			historicoItemOrdemServicoFacade = new HistoricoItemOrdemServicoFacade();
		}

		return historicoItemOrdemServicoFacade;
	}

	public HistoricoItemOrdemServico getHistoricoItemOrdemServico() {
		if (historicoItemOrdemServico == null) {
			historicoItemOrdemServico = new HistoricoItemOrdemServico();
		}

		return historicoItemOrdemServico;
	}

	public void setHistoricoItemOrdemServico(HistoricoItemOrdemServico historicoItemOrdemServico) {
		this.historicoItemOrdemServico = historicoItemOrdemServico;
	}

	public void createHistoricoItemOrdemServico() {
		try {
			/*if (situacao != null){
				historicoOrdemServico.setSituacao(situacao);
			}*/
			if(ordemServico != null){
				historicoItemOrdemServico.setIdOS(ordemServico.getId());
			}
			getHistoricoItemOrdemServicoFacade().createHistoricoItemOrdemServico(historicoItemOrdemServico);
			closeDialog();
			displayInfoMessageToUser("Created With Sucess");
			loadHistoricoItemOrdemServicos();
			resetHistoricoItemOrdemServico();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}
	
	public void updateHistoricoItemOrdemServico() {
		try {
			/*if (situacao != null){
				historicoOrdemServico.setSituacao(situacao);
			}*/
			getHistoricoItemOrdemServicoFacade().updateHistoricoItemOrdemServico(historicoItemOrdemServico);
			closeDialog();
			displayInfoMessageToUser("Updated With Sucess");
			loadHistoricoItemOrdemServicos();
			resetHistoricoItemOrdemServico();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}
	
	public void deleteHistoricoItemOrdemServico() {
		try {
			getHistoricoItemOrdemServicoFacade().deleteHistoricoItemOrdemServico(historicoItemOrdemServico);
			closeDialog();
			displayInfoMessageToUser("Deleted With Sucess");
			loadHistoricoItemOrdemServicos();
			resetHistoricoItemOrdemServico();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public List<HistoricoItemOrdemServico> getAllHistoricoItemOrdemServicos() {
		if (historicoItemOrdemServicos == null) {
			loadHistoricoItemOrdemServicos();
		}

		return historicoItemOrdemServicos;
	}

	private void loadHistoricoItemOrdemServicos() {
		historicoItemOrdemServicos = getHistoricoItemOrdemServicoFacade().listAll();
	}

	public void resetHistoricoItemOrdemServico() {
		historicoItemOrdemServico = new HistoricoItemOrdemServico();
		ordemServico      = new OrdemServico();
		//situacao     = null;
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

		allOrdemServicos.remove(historicoItemOrdemServico.getIdOS());

		for (OrdemServico ordemServico : allOrdemServicos) {
			if (ordemServico.getDsDescricao().toLowerCase().contains(name.toLowerCase())) {
				queryResult.add(ordemServico);
			}
		}

		return queryResult;
	}
	

	/*public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}*/
	
	/*public List<Situacao> completeSituacao(String name) {
		List<Situacao> queryResult = new ArrayList<Situacao>();

		/*if (allClientes == null) {
			clienteFacade = new ClienteFacade();
			allClientes = clienteFacade.listAll();
		}

		allClientes.remove(ordemServico.getCliente());
        */
		/*for (Situacao situacao : Situacao.values()) {
			
				queryResult.add(situacao);
			
		}

		return queryResult;
	}*/
}
