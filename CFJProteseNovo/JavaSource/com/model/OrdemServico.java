package com.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class OrdemServico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name="sequencia_ordem",sequenceName="seq_ordem",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="sequencia_ordem")
	private int id;
	private String dsDescricao;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	//@ManyToOne
	//@JoinColumn(name="servico_id")
	//private Servico servico;
	private String paciente;
	private Date dtEntrada;
	private Date dtSaida;
	private Situacao situacao;
	private BigDecimal preco;
	private Date dtCadastro;
	private int idUser;
	private int idUserAlteracao;
	
	@ManyToOne
	@JoinColumn(name="dentista_id")
	private Dentista dentista;
	
	 public Dentista getDentista() {
		return dentista;
	}
	public void setDentista(Dentista dentista) {
		this.dentista = dentista;
	}
	@OneToMany(mappedBy = "ordemServico", targetEntity = HistoricoOrdemServico.class, fetch = FetchType.LAZY)
	//@Transient
	 private List<HistoricoOrdemServico> historicoOrdemServico;
	
	 @OneToMany(mappedBy = "ordemServico", targetEntity = ItemOrdemServico.class, fetch = FetchType.LAZY)
	 private List<ItemOrdemServico> itemOrdemServico;
	 
	 
	
	public List<ItemOrdemServico> getItemOrdemServico() {
		return itemOrdemServico;
	}
	public void setItemOrdemServico(List<ItemOrdemServico> itemOrdemServico) {
		this.itemOrdemServico = itemOrdemServico;
	}
	public int getId() {
		return id;
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getDtEntrada() {
		return dtEntrada;
	}
	public void setDtEntrada(Date dtEntrada) {
		this.dtEntrada = dtEntrada;
	}
	public Date getDtSaida() {
		return dtSaida;
	}
	public void setDtSaida(Date dtSaida) {
		this.dtSaida = dtSaida;
	}
	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	
	
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OrdemServico) {
			OrdemServico ordemServico = (OrdemServico) obj;
			return ordemServico.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return dsDescricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public List<HistoricoOrdemServico> getHistoricoOrdemServico() {
		return historicoOrdemServico;
	}
	public void setHistoricoOrdemServico(
			List<HistoricoOrdemServico> historicoOrdemServico) {
		this.historicoOrdemServico = historicoOrdemServico;
	}
	public Date getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
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
	//public Servico getServico() {
	//	return servico;
	//}
	//public void setServico(Servico servico) {
	//	this.servico = servico;
	//}

	
	
}
