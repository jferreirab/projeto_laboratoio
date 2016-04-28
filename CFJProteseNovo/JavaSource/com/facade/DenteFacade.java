package com.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.dao.DenteDAO;
import com.model.Dente;

public class DenteFacade implements Serializable {
   
	private static final long serialVersionUID = 1L;
	
	private DenteDAO denteDAO = new DenteDAO();

	public void createDente(Dente dente) {
		dente.setNome(dente.getNome().toUpperCase());
		denteDAO.beginTransaction();
		denteDAO.save(dente);
		denteDAO.commitAndCloseTransaction();
	}

	public void updateDente(Dente dente) {
		denteDAO.beginTransaction();
		Dente persistedDente = denteDAO.find(dente.getId());
		persistedDente.setNome(dente.getNome().toUpperCase());
		persistedDente.setDtAlteracao(new Date());
		persistedDente.setDtCadastro(dente.getDtCadastro());
		persistedDente.setIdUser(dente.getIdUser());
		persistedDente.setIdUserAlteracao(dente.getIdUserAlteracao());
		denteDAO.update(persistedDente);
		denteDAO.commitAndCloseTransaction();
	}

	public Dente findDente(int denteId) {
		denteDAO.beginTransaction();
		Dente dente = denteDAO.find(denteId);
		denteDAO.closeTransaction();
		return dente;
	}

	public List<Dente> listAll() {
		denteDAO.beginTransaction();
		List<Dente> result = denteDAO.findAllOrdenado();
		denteDAO.closeTransaction();
		return result;
	}

	public void deleteDente(Dente dente) {
		denteDAO.beginTransaction();
		Dente persistedDente = denteDAO.findReferenceOnly(dente.getId());
		denteDAO.delete(persistedDente);
		denteDAO.commitAndCloseTransaction();
	}
}
