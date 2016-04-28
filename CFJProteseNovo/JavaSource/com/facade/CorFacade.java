package com.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.dao.CorDAO;
import com.model.Cor;

public class CorFacade implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private CorDAO corDAO = new CorDAO();

	public void createCor(Cor cor) {
		cor.setNome(cor.getNome().toUpperCase());
		corDAO.beginTransaction();
		corDAO.save(cor);
		corDAO.commitAndCloseTransaction();
	}

	public void updateCor(Cor cor) {
		corDAO.beginTransaction();
		Cor persistedCor = corDAO.find(cor.getId());
		persistedCor.setNome(cor.getNome().toUpperCase());
		persistedCor.setDtAlteracao(new Date());
		persistedCor.setDtCadastro(cor.getDtCadastro());
		persistedCor.setIdUser(cor.getIdUser());
		persistedCor.setIdUserAlteracao(cor.getIdUserAlteracao());
		corDAO.update(persistedCor);
		corDAO.commitAndCloseTransaction();
	}

	public Cor findCor(int corId) {
		corDAO.beginTransaction();
		Cor cor = corDAO.find(corId);
		corDAO.closeTransaction();
		return cor;
	}

	public List<Cor> listAll() {
		corDAO.beginTransaction();
		List<Cor> result = corDAO.findAll();
		corDAO.closeTransaction();
		return result;
	}

	public void deleteCor(Cor cor) {
		corDAO.beginTransaction();
		Cor persistedCor = corDAO.findReferenceOnly(cor.getId());
		corDAO.delete(persistedCor);
		corDAO.commitAndCloseTransaction();
	}
}
