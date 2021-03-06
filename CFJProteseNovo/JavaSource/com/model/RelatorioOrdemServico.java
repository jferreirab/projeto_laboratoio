package com.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RelatorioOrdemServico implements Serializable {

	 private static final long  serialVersionUID = 1L;
	 
	 private int clienteId;
	 private int osId;
	 private String clienteNome;
	 private String dentista;
	 private String dsDescricao;
	 private String paciente;
	 private Date dtSaida;
	 private BigDecimal preco;
	 private String situacao;
	 
	public int getClienteId() {
		return clienteId;
	}
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
	
	public int getOsId() {
		return osId;
	}
	public void setOsId(int osId) {
		this.osId = osId;
	}
	public String getClienteNome() {
		return clienteNome;
	}
	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}
	public String getDsDescricao() {
		return dsDescricao;
	}
	public void setDsDescricao(String dsDescricao) {
		this.dsDescricao = dsDescricao;
	}
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public Date getDtSaida() {
		return dtSaida;
	}
	public void setDtSaida(Date dtSaida) {
		this.dtSaida = dtSaida;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public String getDentista() {
		return dentista;
	}
	public void setDentista(String dentista) {
		this.dentista = dentista;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	} 
	 
	 
}
