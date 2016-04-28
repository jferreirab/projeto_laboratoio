package com.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
@Entity
public class Dentista implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="sequencia_dentista",sequenceName="seq_dentista",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="sequencia_dentista")
	private  int id;
	
	private String nome;
	
	private String cro;
	
	private Date dtCadastro;
	private Date dtAlteracao;
	private int idUser;
	private int idUserAlteracao;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "dentista", targetEntity = OrdemServico.class, fetch = FetchType.LAZY)
	private List<OrdemServico> ordemServico;
	 
	@OneToMany(mappedBy = "dentista", targetEntity = HistoricoOrdemServico.class, fetch = FetchType.LAZY)
	private List<HistoricoOrdemServico> historicoOrdemServico;
	
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
		if (obj instanceof Cor) {
			Cor cor = (Cor) obj;
			return cor.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getCro() {
		return cro;
	}
	public void setCro(String cro) {
		this.cro = cro;
	}
	public List<OrdemServico> getOrdemServico() {
		return ordemServico;
	}
	public void setOrdemServico(List<OrdemServico> ordemServico) {
		this.ordemServico = ordemServico;
	}
	public List<HistoricoOrdemServico> getHistoricoOrdemServico() {
		return historicoOrdemServico;
	}
	public void setHistoricoOrdemServico(
			List<HistoricoOrdemServico> historicoOrdemServico) {
		this.historicoOrdemServico = historicoOrdemServico;
	}
	
	
	
}
