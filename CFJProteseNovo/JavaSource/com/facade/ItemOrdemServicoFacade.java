package com.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;

import org.hibernate.criterion.Order;


import com.dao.ItemOrdemServicoDAO;
import com.model.Dente;
import com.model.ItemOrdemServico;
import com.model.OrdemServico;

public class ItemOrdemServicoFacade implements Serializable {
	 private static final long serialVersionUID = 1L;
		
		private ItemOrdemServicoDAO itemOrdemServicoDAO = new ItemOrdemServicoDAO();

		public void createItemOrdemServico(ItemOrdemServico itemOrdemServico) {
			
			itemOrdemServico.setObservacao(itemOrdemServico.getObservacao().toUpperCase());
			//itemOrdemServico.setPaciente(itemOrdemServico.getPaciente().toUpperCase());
			itemOrdemServicoDAO.beginTransaction();
			itemOrdemServicoDAO.save(itemOrdemServico);
			itemOrdemServicoDAO.commitAndCloseTransaction();
		}

		public void updateItemOrdemServico(ItemOrdemServico itemOrdemServico) {
			itemOrdemServicoDAO.beginTransaction();
			/*ItemOrdemServico persistedOrdemServico = itemOrdemServicoDAO.find(itemOrdemServico.getId());
			persistedOrdemServico.setCliente(itemOrdemServico.getCliente());
			//persistedOrdemServico.setServico(itemOrdemServico.getServico());
			persistedOrdemServico.setDsDescricao(itemOrdemServico.getDsDescricao().toUpperCase());
			persistedOrdemServico.setDtEntrada(itemOrdemServico.getDtEntrada());
			persistedOrdemServico.setDtSaida(itemOrdemServico.getDtSaida());
			persistedOrdemServico.setSituacao(itemOrdemServico.getSituacao());
			persistedOrdemServico.setPreco(itemOrdemServico.getPreco());
			persistedOrdemServico.setPaciente(itemOrdemServico.getPaciente().toUpperCase());
			persistedOrdemServico.setIdUserAlteracao(itemOrdemServico.getIdUserAlteracao());
			persistedOrdemServico.setDtCadastro(itemOrdemServico.getDtCadastro());*/
			itemOrdemServicoDAO.update(itemOrdemServico);
			itemOrdemServicoDAO.commitAndCloseTransaction();
		}

		public ItemOrdemServico findItemOrdemServico(int itemOrdemServicoId) {
			itemOrdemServicoDAO.beginTransaction();
			ItemOrdemServico itemOrdemServico = itemOrdemServicoDAO.find(itemOrdemServicoId);
			itemOrdemServicoDAO.closeTransaction();
			return itemOrdemServico;
		}

		public List<ItemOrdemServico> listAll() {
			itemOrdemServicoDAO.beginTransaction();
			List<ItemOrdemServico> result = itemOrdemServicoDAO.findAll();
			itemOrdemServicoDAO.closeTransaction();
			return result;
		}

		public void deleteItemOrdemServico(ItemOrdemServico itemOrdemServico) {
			itemOrdemServicoDAO.beginTransaction();
			ItemOrdemServico persistedOrdemServico = itemOrdemServicoDAO.findReferenceOnly(itemOrdemServico.getId());
			itemOrdemServicoDAO.delete(persistedOrdemServico);
			itemOrdemServicoDAO.commitAndCloseTransaction();
		}
		
	public List<ItemOrdemServico> recuperaItemOrdemServico(ItemOrdemServico itemOrdemServico) {
			
			//int id = itemOrdemServico.getId();
			itemOrdemServicoDAO.beginTransaction();
					
			Session session = (Session) itemOrdemServicoDAO.getEm().getDelegate();
			 
			Criteria crit = session.createCriteria(ItemOrdemServico.class);
	        
			/*if (itemOrdemServico != null){
				
				if (itemOrdemServico.getCliente() != null && itemOrdemServico.getCliente().getId() != 0 ){
					Criterion Criterion = Restrictions.eq( "cliente",  itemOrdemServico.getCliente());
					crit.add( Criterion );
				}
				if(itemOrdemServico.getId() != 0 ){
					Criterion Criterion = Restrictions.eq( "id",  itemOrdemServico.getId());
					crit.add( Criterion );
					
				}
				if(itemOrdemServico.getDtEntrada() != null ){
					Criterion diaIni = Restrictions.ge( "dtEntrada", itemOrdemServico.getDtEntrada() );
					crit.add( diaIni );
				}
				if(itemOrdemServico.getDtSaida() != null){
					Criterion diaFim = Restrictions.le( "dtSaida", itemOrdemServico.getDtSaida() );
					crit.add(diaFim);
				}
				if(itemOrdemServico.getPaciente() != null && itemOrdemServico.getPaciente().compareTo("") != 0){
					Criterion paciCriterion  = Restrictions.like("paciente", itemOrdemServico.getPaciente()+"%");
					crit.add(paciCriterion);
				}
				if(itemOrdemServico.getSituacao()!= null ){
					Criterion situacaoCriterion  = Restrictions.eq("situacao", itemOrdemServico.getSituacao());
					crit.add(situacaoCriterion);
				}
			}*/
		    

		    crit.addOrder( Order.asc( "dtEntrada" ) );
		    
			@SuppressWarnings("unchecked")
			List<ItemOrdemServico> result =  crit.list(); 
			itemOrdemServicoDAO.closeTransaction();
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
	
	public boolean verificaAlteracao(OrdemServico oS) throws PersistenceException{
		
		if(oS.getItemOrdemServico() == null || oS.getItemOrdemServico().size() == 0 ){
			throw new PersistenceException("Não foi informado os Itens da OS. Favor Verificar!");
			
		}
		return true;
		
	}
}
