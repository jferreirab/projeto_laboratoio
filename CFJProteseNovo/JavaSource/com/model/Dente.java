package com.model;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;

@Entity
public class Dente implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="sequencia_dente",sequenceName="seq_dente",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="sequencia_dente")
	private int id;
	private String nome;
	private Date dtCadastro;
	private Date dtAlteracao;
	private int idUser;
	private int idUserAlteracao;
	
	//@OneToMany(mappedBy = "dente", targetEntity = ItemOrdemServico.class, fetch = FetchType.LAZY)
	//private List<ItemOrdemServico> itemOrdemServico;
	
	
	 
	//public List<ItemOrdemServico> getItemOrdemServico() {
	//	return itemOrdemServico;
	//}
	//public void setItemOrdemServico(List<ItemOrdemServico> itemOrdemServico) {
	//	this.itemOrdemServico = itemOrdemServico;
	//}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	
	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Dente) {
			Dente dente = (Dente) obj;
			return dente.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}
}
