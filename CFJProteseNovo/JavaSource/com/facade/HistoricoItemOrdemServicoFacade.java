package com.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.dao.HistoricoItemOrdemServicoDAO;
import com.model.Dente;
import com.model.HistoricoItemOrdemServico;

public class HistoricoItemOrdemServicoFacade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private HistoricoItemOrdemServicoDAO historicoItemOrdemServicoDAO = new HistoricoItemOrdemServicoDAO();

	public void createHistoricoItemOrdemServico(HistoricoItemOrdemServico historicoItemOrdemServico) {
		
		historicoItemOrdemServico.setObservacao(historicoItemOrdemServico.getObservacao().toUpperCase());
		historicoItemOrdemServicoDAO.beginTransaction();
		historicoItemOrdemServicoDAO.save(historicoItemOrdemServico);
		historicoItemOrdemServicoDAO.commitAndCloseTransaction();
	}

	public void updateHistoricoItemOrdemServico(HistoricoItemOrdemServico historicoItemOrdemServico) {
		historicoItemOrdemServicoDAO.beginTransaction();
		historicoItemOrdemServicoDAO.update(historicoItemOrdemServico);
		historicoItemOrdemServicoDAO.commitAndCloseTransaction();
	}

	public HistoricoItemOrdemServico findHistoricoItemOrdemServico(int historicoItemOrdemServicoId) {
		historicoItemOrdemServicoDAO.beginTransaction();
		HistoricoItemOrdemServico historicoItemOrdemServico = historicoItemOrdemServicoDAO.find(historicoItemOrdemServicoId);
		historicoItemOrdemServicoDAO.closeTransaction();
		return historicoItemOrdemServico;
	}

	public List<HistoricoItemOrdemServico> listAll() {
		historicoItemOrdemServicoDAO.beginTransaction();
		List<HistoricoItemOrdemServico> result = historicoItemOrdemServicoDAO.findAll();
		historicoItemOrdemServicoDAO.closeTransaction();
		return result;
	}

	public void deleteHistoricoItemOrdemServico(HistoricoItemOrdemServico historicoItemOrdemServico) {
		historicoItemOrdemServicoDAO.beginTransaction();
		HistoricoItemOrdemServico persistedOrdemServico = historicoItemOrdemServicoDAO.findReferenceOnly(historicoItemOrdemServico.getId());
		historicoItemOrdemServicoDAO.delete(persistedOrdemServico);
		historicoItemOrdemServicoDAO.commitAndCloseTransaction();
	}
	
public List<HistoricoItemOrdemServico> recuperaHistoricoItemOrdemServico(HistoricoItemOrdemServico historicoItemOrdemServico) {
		
	    historicoItemOrdemServicoDAO.beginTransaction();
				
		Session session = (Session) historicoItemOrdemServicoDAO.getEm().getDelegate();
		 
		Criteria crit = session.createCriteria(HistoricoItemOrdemServico.class);
        
	    crit.addOrder( Order.asc( "dtEntrada" ) );
	    
		@SuppressWarnings("unchecked")
		List<HistoricoItemOrdemServico> result =  crit.list(); 
		historicoItemOrdemServicoDAO.closeTransaction();
		return result;
	}

public List<String> dentesListaString(List<Dente> listaDentes){
	List<String> lista = new ArrayList<String>();
	
	for(Dente den : listaDentes){
		lista.add(den.getNome());
	}
	return lista;
}

public String deteString(List<Dente> listaDentes){
	String lista = "";
	int count = 1;
	for(Dente den : listaDentes){
		if(count == 1){
			lista = den.getNome();
		}
		else{
		    lista = lista + " - "+ den.getNome();
		}
		count = 2;
	}
	return lista;
}
}
