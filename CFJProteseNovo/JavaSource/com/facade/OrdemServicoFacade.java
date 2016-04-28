package com.facade;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


import com.dao.OrdemServicoDAO;


import com.model.OrdemServico;

public class OrdemServicoFacade implements Serializable {
    private static final long serialVersionUID = 1L;
	
	private OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();

	public void createOrdemServico(OrdemServico ordemServico) {
		
		ordemServico.setDsDescricao(ordemServico.getDsDescricao().toUpperCase());
		ordemServico.setPaciente(ordemServico.getPaciente().toUpperCase());
		ordemServicoDAO.beginTransaction();
		ordemServicoDAO.save(ordemServico);
		ordemServicoDAO.commitAndCloseTransaction();
	}

	public void updateOrdemServico(OrdemServico ordemServico) {
		ordemServicoDAO.beginTransaction();
		OrdemServico persistedOrdemServico = ordemServicoDAO.find(ordemServico.getId());
		persistedOrdemServico.setCliente(ordemServico.getCliente());
		persistedOrdemServico.setDentista(ordemServico.getDentista());
		//persistedOrdemServico.setServico(ordemServico.getServico());
		persistedOrdemServico.setDsDescricao(ordemServico.getDsDescricao().toUpperCase());
		persistedOrdemServico.setDtEntrada(ordemServico.getDtEntrada());
		persistedOrdemServico.setDtSaida(ordemServico.getDtSaida());
		persistedOrdemServico.setSituacao(ordemServico.getSituacao());
		persistedOrdemServico.setPreco(ordemServico.getPreco());
		persistedOrdemServico.setPaciente(ordemServico.getPaciente().toUpperCase());
		persistedOrdemServico.setIdUserAlteracao(ordemServico.getIdUserAlteracao());
		persistedOrdemServico.setDtCadastro(ordemServico.getDtCadastro());
		ordemServicoDAO.update(persistedOrdemServico);
		ordemServicoDAO.commitAndCloseTransaction();
	}

	public OrdemServico findOrdemServico(int ordemServicoId) {
		ordemServicoDAO.beginTransaction();
		OrdemServico ordemServico = ordemServicoDAO.find(ordemServicoId);
		ordemServicoDAO.closeTransaction();
		return ordemServico;
	}

	public List<OrdemServico> listAll() {
		ordemServicoDAO.beginTransaction();
		List<OrdemServico> result = ordemServicoDAO.findAll();
		ordemServicoDAO.closeTransaction();
		return result;
	}

	public void deleteOrdemServico(OrdemServico ordemServico) {
		ordemServicoDAO.beginTransaction();
		OrdemServico persistedOrdemServico = ordemServicoDAO.findReferenceOnly(ordemServico.getId());
		ordemServicoDAO.delete(persistedOrdemServico);
		ordemServicoDAO.commitAndCloseTransaction();
	}
	
public List<OrdemServico> recuperaOrdemServico(OrdemServico ordemServico) {
		
		//int id = ordemServico.getId();
		ordemServicoDAO.beginTransaction();
				
		Session session = (Session) ordemServicoDAO.getEm().getDelegate();
		 
		Criteria crit = session.createCriteria(OrdemServico.class);
        
		if (ordemServico != null){
			
			if (ordemServico.getCliente() != null && ordemServico.getCliente().getId() != 0 ){
				Criterion Criterion = Restrictions.eq( "cliente",  ordemServico.getCliente());
				crit.add( Criterion );
			}
			if (ordemServico.getDentista() != null && ordemServico.getDentista().getId() != 0 ){
				Criterion Criterion = Restrictions.eq( "dentista",  ordemServico.getDentista());
				crit.add( Criterion );
			}
			if(ordemServico.getId() != 0 ){
				Criterion Criterion = Restrictions.eq( "id",  ordemServico.getId());
				crit.add( Criterion );
				
			}
			if(ordemServico.getDtEntrada() != null ){
				Criterion diaIni = Restrictions.ge( "dtEntrada", ordemServico.getDtEntrada() );
				crit.add( diaIni );
			}
			if(ordemServico.getDtSaida() != null){
				Criterion diaFim = Restrictions.le( "dtSaida", ordemServico.getDtSaida() );
				crit.add(diaFim);
			}
			if(ordemServico.getPaciente() != null && ordemServico.getPaciente().compareTo("") != 0){
				Criterion paciCriterion  = Restrictions.like("paciente", "%"+ordemServico.getPaciente().toUpperCase()+"%");
				crit.add(paciCriterion);
			}
			if(ordemServico.getSituacao()!= null ){
				Criterion situacaoCriterion  = Restrictions.eq("situacao", ordemServico.getSituacao());
				crit.add(situacaoCriterion);
			}
		}
	    

	    crit.addOrder( Order.asc( "dtEntrada" ) );
	    
		@SuppressWarnings("unchecked")
		List<OrdemServico> result =  crit.list(); 
		ordemServicoDAO.closeTransaction();
		return result;
	}

public OrdemServico recuperaOrdemServico2(OrdemServico ordemServico) {
	
	//int id = ordemServico.getId();
	ordemServicoDAO.beginTransaction();
			
	Session session = (Session) ordemServicoDAO.getEm().getDelegate();
	 
	Criteria crit = session.createCriteria(OrdemServico.class);
    
	if (ordemServico != null){
		
		if (ordemServico.getCliente() != null && ordemServico.getCliente().getId() != 0 ){
			Criterion Criterion = Restrictions.eq( "cliente",  ordemServico.getCliente());
			crit.add( Criterion );
		}
		if(ordemServico.getId() != 0 ){
			Criterion Criterion = Restrictions.eq( "id",  ordemServico.getId());
			crit.add( Criterion );
			
		}
		if(ordemServico.getDtEntrada() != null ){
			Criterion diaIni = Restrictions.ge( "dtEntrada", ordemServico.getDtEntrada() );
			crit.add( diaIni );
		}
		if(ordemServico.getDtSaida() != null){
			Criterion diaFim = Restrictions.le( "dtSaida", ordemServico.getDtSaida() );
			crit.add(diaFim);
		}
		if(ordemServico.getPaciente() != null && ordemServico.getPaciente().compareTo("") != 0){
			Criterion paciCriterion  = Restrictions.like("paciente", "%"+ordemServico.getPaciente().toUpperCase()+"%");
			crit.add(paciCriterion);
		}
		if(ordemServico.getSituacao()!= null ){
			Criterion situacaoCriterion  = Restrictions.eq("situacao", ordemServico.getSituacao());
			crit.add(situacaoCriterion);
		}
	}
    

    crit.addOrder( Order.asc( "dtEntrada" ) );
    
	
	OrdemServico result =  (OrdemServico) crit.list(); 
	ordemServicoDAO.closeTransaction();
	return result;
}
}
