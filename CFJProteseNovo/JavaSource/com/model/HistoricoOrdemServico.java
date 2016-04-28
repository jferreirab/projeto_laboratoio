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
public class HistoricoOrdemServico implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name="seq_historico",sequenceName="seq_historico",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_historico")
	private int id;
	
    @ManyToOne
	@JoinColumn(name="ordem_Servico_id")
	private OrdemServico ordemServico;
	
	//private int idOS;
	
	private String dsDescricao;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="dentista_id")
	private Dentista dentista;
	//@ManyToOne
	//@JoinColumn(name="servico_id")
	//private Servico servico;
	private String paciente;
	
	private Date dtEntrada;
	private Date dtSaida;
	private Situacao situacao;
	private BigDecimal preco;
	private Date dtAlteracao;
	
	private int userAlteracao;
	
	 @OneToMany(mappedBy = "historicoOrdemServico", targetEntity = HistoricoItemOrdemServico.class, fetch = FetchType.LAZY)
	 private List<HistoricoItemOrdemServico> historicoItemOrdemServicos;
	
	public Date getDtAlteracao() {
		return dtAlteracao;
	}
	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}
	
	public int getIdUserAlteracao() {
		return userAlteracao;
	}
	public void setIdUserAlteracao(int idUserAlteracao) {
		this.userAlteracao = idUserAlteracao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}
	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
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
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
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
	//public Servico getServico() {
	//	return servico;
	//}
	//public void setServico(Servico servico) {
	//	this.servico = servico;
	//}
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
		if (obj instanceof HistoricoOrdemServico) {
			HistoricoOrdemServico historicoOrdemServico = (HistoricoOrdemServico) obj;
			return historicoOrdemServico.getId() == id;
		}

		return false;
	}
	/*public int getIdOS() {
		return idOS;
	}
	public void setIdOS(int idOS) {
		this.idOS = idOS;
	}*/
	public List<HistoricoItemOrdemServico> getHistoricoItemOrdemServicos() {
		return historicoItemOrdemServicos;
	}
	public void setHistoricoItemOrdemServicos(
			List<HistoricoItemOrdemServico> historicoItemOrdemServicos) {
		this.historicoItemOrdemServicos = historicoItemOrdemServicos;
	}
	public Dentista getDentista() {
		return dentista;
	}
	public void setDentista(Dentista dentista) {
		this.dentista = dentista;
	}
	
	

}
