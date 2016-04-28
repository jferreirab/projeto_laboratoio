package com.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Servico implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="sequencia_servico",sequenceName="seq_serv",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="sequencia_servico")
	private int id;
	private String dsDescricao;
	private Date dtCadastro;
	private Date dtAlteracao;
	private int idUser;
	private int idUserAlteracao;
	
	
	 @OneToMany(mappedBy = "servico", targetEntity = ItemOrdemServico.class, fetch = FetchType.LAZY)
	 private List<ItemOrdemServico> itemOrdemServico;
	 
	// @OneToMany(mappedBy = "servico", targetEntity = OrdemServico.class, fetch = FetchType.LAZY)
	// private List<OrdemServico> ordemServico;
	 
	 
    
	// @OneToMany(mappedBy = "servico", targetEntity = HistoricoOrdemServico.class, fetch = FetchType.LAZY)
	 //private List<HistoricoOrdemServico> historicoOrdemServico;
	 
	 
	 
	//public List<HistoricoOrdemServico> getHistoricoOrdemServico() {
	//	return historicoOrdemServico;
	//}

	//public void setHistoricoOrdemServico(
	//		List<HistoricoOrdemServico> historicoOrdemServico) {
	//	this.historicoOrdemServico = historicoOrdemServico;
	//}

	//public List<OrdemServico> getOrdemServico() {
	//	return ordemServico;
	//}

	//public void setOrdemServico(List<OrdemServico> ordemServico) {
	//	this.ordemServico = ordemServico;
	//}
	
	 @OneToMany(mappedBy = "servico", targetEntity = HistoricoItemOrdemServico.class, fetch = FetchType.LAZY)
	 private List<HistoricoItemOrdemServico> historicoItemOrdemServico ;
	 
	public int getId() {
		return id;
	}
	public List<ItemOrdemServico> getItemOrdemServico() {
		return itemOrdemServico;
	}
	public void setItemOrdemServico(List<ItemOrdemServico> itemOrdemServico) {
		this.itemOrdemServico = itemOrdemServico;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDsDescricao() {
		return dsDescricao;
	}
	public void setDsDescricao(String dsDescricao) {
		this.dsDescricao = dsDescricao;
	}
	public Date getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	public Date getDtAlteracao() {
		return dtAlteracao;
	}
	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdUserAlteracao() {
		return idUserAlteracao;
	}
	public void setIdUserAlteracao(int idUserAlteracao) {
		this.idUserAlteracao = idUserAlteracao;
	}
	
	
	public List<HistoricoItemOrdemServico> getHistoricoItemOrdemServico() {
		return historicoItemOrdemServico;
	}
	public void setHistoricoItemOrdemServico(
			List<HistoricoItemOrdemServico> historicoItemOrdemServico) {
		this.historicoItemOrdemServico = historicoItemOrdemServico;
	}
	
	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Servico) {
			Servico servico = (Servico) obj;
			return servico.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return dsDescricao;
	}

}
