package com.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.dao.OrdemServicoDAO;

import com.model.ItemOrdemServico;
import com.model.OrdemServico;
import com.model.RelatorioOrdemServico;
import com.model.Situacao;


public class RelatorioOrdemServicoFacade implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	 private ItemOrdemServicoFacade itemOrdemServicoFacade;
		
	// private RelatorioOrdemServicoDAO relatorioOrdemServicoDAO = new RelatorioOrdemServicoDAO();
	 private OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
	 
	 public ItemOrdemServicoFacade getItemOrdemServicoFacade(){
			if (itemOrdemServicoFacade == null){
				itemOrdemServicoFacade = new ItemOrdemServicoFacade();
			}
			return itemOrdemServicoFacade;
		}
	 
	 public Collection<RelatorioOrdemServico> recuperaOrdemServico(OrdemServico ordemServico) throws Exception {
			
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
					Criterion paciCriterion  = Restrictions.ilike("paciente", ordemServico.getPaciente());
					crit.add(paciCriterion);
				}
				if(ordemServico.getDentista() != null && ordemServico.getDentista().getId() != 0 ){
					Criterion criterion = Restrictions.eq("dentista", ordemServico.getDentista());
					crit.add(criterion);
				}
				if(ordemServico.getSituacao()!= null ){
					Criterion situacaoCriterion  = Restrictions.eq("situacao", ordemServico.getSituacao());
					crit.add(situacaoCriterion);
				}
				else{
					throw new Exception( "Favor Informar uma Situação para recuperar os dados do Relatorio! ");
				}
				//Criterion situacaoCriterion  = Restrictions.eq("situacao", Situacao.FINALIZADA);
				//crit.add(situacaoCriterion);
			}
		    

		   // crit.addOrder( Order.asc( "dtEntrada" ) );
		    crit.addOrder(Order.asc("cliente"));
		    crit.addOrder(Order.asc("dtSaida"));
		    
		    
		    
			Collection<RelatorioOrdemServico> relatorioOrdemServicos =  new ArrayList<RelatorioOrdemServico>(); 
		    
		    @SuppressWarnings("unchecked")
			List<OrdemServico> result =  crit.list(); 
		    
		    if (result == null){
		    	throw new Exception( "Para os filtros informados não foi encontrado nenhum registro! ");
		    }
		    for (OrdemServico ordemServico2 : result){
		    	RelatorioOrdemServico rel = new RelatorioOrdemServico();
		    	rel.setClienteId(ordemServico2.getCliente().getId());
		    	rel.setClienteNome(ordemServico2.getCliente().getName());
		    	//insere item no relatorio de ordem de serviço
		    	String servico = null;
		    	for(ItemOrdemServico item : ordemServico2.getItemOrdemServico()){
		    	    
		    		if(servico == null){
		    			servico = item.getServico().getDsDescricao();
		    		}
		    		else{
		    			servico = servico + " | " + item.getServico().getDsDescricao();
		    		}
		    		if(item.getDentes() != null){
						item.setListadentes(getItemOrdemServicoFacade().dentesListaString(item.getDentes()));
						item.setDentesLista(getItemOrdemServicoFacade().deteString(item.getDentes()));
						if(item.getDentesLista() != ""){
							servico = servico +" - Dentes: "+item.getDentesLista();
						}
					}
		    		if(item.getCor() != null){
		    			servico = servico + " - Cor: "+item.getCor().getNome();
		    		}
		    		
		    	}
		    	
		    	rel.setDsDescricao(servico);
		    	
		    	rel.setDtSaida(ordemServico2.getDtSaida());
		    	rel.setOsId(ordemServico2.getId());
		    	rel.setPaciente(ordemServico2.getPaciente());
		    	rel.setPreco(ordemServico2.getPreco());
		    	rel.setDentista(ordemServico2.getDentista().getNome());
		    	rel.setSituacao(ordemServico2.getSituacao().toString());
		    	relatorioOrdemServicos.add(rel);
		    	
		    }
		    
			
			ordemServicoDAO.closeTransaction();
			return relatorioOrdemServicos;
		}
}
