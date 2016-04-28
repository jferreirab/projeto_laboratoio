package com.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.dao.ServicoDAO;
import com.model.Servico;

public class ServicoFacade implements Serializable {
  
private static final long serialVersionUID = 1L;
	
	private ServicoDAO servicoDAO = new ServicoDAO();

	public void createServico(Servico servico) {
		servico.setDsDescricao(servico.getDsDescricao().toUpperCase());
		servicoDAO.beginTransaction();
		servicoDAO.save(servico);
		servicoDAO.commitAndCloseTransaction();
	}

	public void updateServico(Servico servico) {
		servicoDAO.beginTransaction();
		Servico persistedServico = servicoDAO.find(servico.getId());
		persistedServico.setDsDescricao(servico.getDsDescricao().toUpperCase());
		persistedServico.setDtAlteracao(new Date());
		persistedServico.setDtCadastro(servico.getDtCadastro());
		persistedServico.setIdUser(servico.getIdUser());
		persistedServico.setIdUserAlteracao(servico.getIdUserAlteracao());
		servicoDAO.update(persistedServico);
		servicoDAO.commitAndCloseTransaction();
	}

	public Servico findServico(int servicoId) {
		servicoDAO.beginTransaction();
		Servico servico = servicoDAO.find(servicoId);
		servicoDAO.closeTransaction();
		return servico;
	}

	public List<Servico> listAll() {
		servicoDAO.beginTransaction();
		List<Servico> result = servicoDAO.findAll();
		servicoDAO.closeTransaction();
		return result;
	}

	public void deleteServico(Servico servico) {
		servicoDAO.beginTransaction();
		Servico persistedServico = servicoDAO.findReferenceOnly(servico.getId());
		servicoDAO.delete(persistedServico);
		servicoDAO.commitAndCloseTransaction();
	}
}
