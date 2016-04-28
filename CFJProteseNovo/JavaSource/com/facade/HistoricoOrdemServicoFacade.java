package com.facade;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.dao.HistoricoOrdemServicoDAO;
import com.model.HistoricoOrdemServico;
import com.model.OrdemServico;

public class HistoricoOrdemServicoFacade implements Serializable {
   
	private static final long serialVersionUID = 1L;
	
	private HistoricoOrdemServicoDAO historicoOrdemServicoDAO = new HistoricoOrdemServicoDAO();
	public void createHistoricoOrdemServico(HistoricoOrdemServico historicoOrdemServico) {
		historicoOrdemServicoDAO.beginTransaction();
		historicoOrdemServicoDAO.save(historicoOrdemServico);
		historicoOrdemServicoDAO.commitAndCloseTransaction();
	}

	public void updateHistoricoOrdemServico(HistoricoOrdemServico historicoOrdemServico) {
		historicoOrdemServicoDAO.beginTransaction();
		HistoricoOrdemServico persistedHistoricoOrdemServico = historicoOrdemServicoDAO.find(historicoOrdemServico.getId());
		persistedHistoricoOrdemServico.setDtEntrada(historicoOrdemServico.getDtEntrada());
		persistedHistoricoOrdemServico.setDtSaida(historicoOrdemServico.getDtSaida());
		persistedHistoricoOrdemServico.setSituacao(historicoOrdemServico.getSituacao());
		persistedHistoricoOrdemServico.setPreco(historicoOrdemServico.getPreco());
		historicoOrdemServicoDAO.update(persistedHistoricoOrdemServico);
		historicoOrdemServicoDAO.commitAndCloseTransaction();
	}

	public HistoricoOrdemServico findHistoricoOrdemServico(int historicoOrdemServicoId) {
		historicoOrdemServicoDAO.beginTransaction();
		HistoricoOrdemServico historicoOrdemServico = historicoOrdemServicoDAO.find(historicoOrdemServicoId);
		historicoOrdemServicoDAO.closeTransaction();
		return historicoOrdemServico;
	}

	public List<HistoricoOrdemServico> listAll() {
		historicoOrdemServicoDAO.beginTransaction();
		List<HistoricoOrdemServico> result = historicoOrdemServicoDAO.findAll();
		historicoOrdemServicoDAO.closeTransaction();
		return result;
	}

	public void deleteHistoricoOrdemServico(HistoricoOrdemServico historicoOrdemServico) {
		historicoOrdemServicoDAO.beginTransaction();
		HistoricoOrdemServico persistedHistoricoOrdemServico = historicoOrdemServicoDAO.findReferenceOnly(historicoOrdemServico.getId());
		historicoOrdemServicoDAO.delete(persistedHistoricoOrdemServico);
		historicoOrdemServicoDAO.commitAndCloseTransaction();
	}
	
	public List<HistoricoOrdemServico> listAll(OrdemServico ordemServico) {
		
		//int id = ordemServico.getId();
		historicoOrdemServicoDAO.beginTransaction();
				
		 Session session = (Session) historicoOrdemServicoDAO.getEm().getDelegate();
		 
		 Criteria crit = session.createCriteria(HistoricoOrdemServico.class);
        
		Criterion hisCriterion = Restrictions.eq( "ordemServico",  ordemServico);

	        crit.add( hisCriterion );

	        crit.addOrder( Order.asc( "dtEntrada" ) );

	     //   return crit.list();
		
		
		@SuppressWarnings("unchecked")
		List<HistoricoOrdemServico> result =  crit.list(); // historicoOrdemServicoDAO.findAll();
		historicoOrdemServicoDAO.closeTransaction();
		return result;
	}
	
}
