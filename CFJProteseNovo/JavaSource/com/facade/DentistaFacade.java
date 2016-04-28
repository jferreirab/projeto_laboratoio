package com.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.dao.DentistaDAO;
import com.model.Cliente;
import com.model.Dentista;
import com.model.OrdemServico;

public class DentistaFacade implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private DentistaDAO dentistaDAO = new DentistaDAO();

	public void createDentista(Dentista dentista) {
		dentista.setNome(dentista.getNome().toUpperCase());
		dentistaDAO.beginTransaction();
		dentistaDAO.save(dentista);
		dentistaDAO.commitAndCloseTransaction();
	}

	public void updateDentista(Dentista dentista) {
		dentistaDAO.beginTransaction();
		Dentista persistedDentista = dentistaDAO.find(dentista.getId());
		persistedDentista.setNome(dentista.getNome().toUpperCase());
		persistedDentista.setDtAlteracao(new Date());
		persistedDentista.setCliente(dentista.getCliente());
		persistedDentista.setCro(dentista.getCro());
		persistedDentista.setDtCadastro(dentista.getDtCadastro());
		persistedDentista.setIdUser(dentista.getIdUser());
		persistedDentista.setIdUserAlteracao(dentista.getIdUserAlteracao());
		dentistaDAO.update(persistedDentista);
		dentistaDAO.commitAndCloseTransaction();
	}

	public Dentista findDentista(int dentistaId) {
		dentistaDAO.beginTransaction();
		Dentista dentista = dentistaDAO.find(dentistaId);
		dentistaDAO.closeTransaction();
		return dentista;
	}

	public List<Dentista> listAll() {
		dentistaDAO.beginTransaction();
		List<Dentista> result = dentistaDAO.findAll();
		dentistaDAO.closeTransaction();
		return result;
	}

	public void deleteDentista(Dentista dentista) {
		dentistaDAO.beginTransaction();
		Dentista persistedDentista = dentistaDAO.findReferenceOnly(dentista.getId());
		dentistaDAO.delete(persistedDentista);
		dentistaDAO.commitAndCloseTransaction();
	}
public List<Dentista> recuperaDesntistasClinica(Cliente cliente) {
		
		
		dentistaDAO.beginTransaction();
				
		Session session = (Session) dentistaDAO.getEm().getDelegate();
		 
		Criteria crit = session.createCriteria(Dentista.class);
        
		if (cliente != null){
			
			if (cliente.getName() != null && cliente.getId() != 0 ){
				Criterion Criterion = Restrictions.eq( "cliente",  cliente);
				crit.add( Criterion );
			}
							
		}
	    

	    crit.addOrder( Order.asc( "nome" ) );
	    
		@SuppressWarnings("unchecked")
		List<Dentista> result =  crit.list(); 
		dentistaDAO.closeTransaction();
		return result;
	}
}
