package com.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.facade.ItemOrdemServicoFacade;
import com.facade.OrdemServicoFacade;
import com.model.ItemOrdemServico;
import com.model.OrdemServico;


@ViewScoped
@ManagedBean
public class ItemOrdemServicoMB extends AbstractMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private ItemOrdemServico itemOrdemServico;
	private List<ItemOrdemServico> itemOrdemServicos;
	private ItemOrdemServicoFacade itemOrdemServicoFacade;
	private OrdemServicoFacade ordemServicoFacade;
	private OrdemServico ordemServico;
	private List<OrdemServico> allOrdemServicos;
	//private Situacao situacao;

	public ItemOrdemServicoFacade getItemOrdemServicoFacade() {
		if (itemOrdemServicoFacade == null) {
			itemOrdemServicoFacade = new ItemOrdemServicoFacade();
		}

		return itemOrdemServicoFacade;
	}

	public ItemOrdemServico getItemOrdemServico() {
		if (itemOrdemServico == null) {
			itemOrdemServico = new ItemOrdemServico();
		}

		return itemOrdemServico;
	}

	public void setItemOrdemServico(ItemOrdemServico itemOrdemServico) {
		this.itemOrdemServico = itemOrdemServico;
	}

	public void createItemOrdemServico() {
		try {
			/*if (situacao != null){
				historicoOrdemServico.setSituacao(situacao);
			}*/
			if(ordemServico != null){
				itemOrdemServico.setOrdemServico(ordemServico);
			}
			getItemOrdemServicoFacade().createItemOrdemServico(itemOrdemServico);
			closeDialog();
			displayInfoMessageToUser("Created With Sucess");
			loadItemOrdemServicos();
			resetItemOrdemServico();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}
	
	public void updateItemOrdemServico() {
		try {
			/*if (situacao != null){
				historicoOrdemServico.setSituacao(situacao);
			}*/
			getItemOrdemServicoFacade().updateItemOrdemServico(itemOrdemServico);
			closeDialog();
			displayInfoMessageToUser("Updated With Sucess");
			loadItemOrdemServicos();
			resetItemOrdemServico();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}
	
	public void deleteItemOrdemServico() {
		try {
			getItemOrdemServicoFacade().deleteItemOrdemServico(itemOrdemServico);
			closeDialog();
			displayInfoMessageToUser("Deleted With Sucess");
			loadItemOrdemServicos();
			resetItemOrdemServico();
		} catch (Exception e) {
			keepDialogOpen();
			displayErrorMessageToUser("Ops, we could not create. Try again later");
			e.printStackTrace();
		}
	}

	public List<ItemOrdemServico> getAllItemOrdemServicos() {
		if (itemOrdemServicos == null) {
			loadItemOrdemServicos();
		}

		return itemOrdemServicos;
	}

	private void loadItemOrdemServicos() {
		itemOrdemServicos = getItemOrdemServicoFacade().listAll();
	}

	public void resetItemOrdemServico() {
		itemOrdemServico = new ItemOrdemServico();
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

		allOrdemServicos.remove(itemOrdemServico.getOrdemServico());

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
