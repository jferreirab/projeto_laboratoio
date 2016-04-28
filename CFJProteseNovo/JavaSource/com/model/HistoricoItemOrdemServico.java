package com.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
public class HistoricoItemOrdemServico implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="seq_hist_item_os",sequenceName="seq_hist_item_os",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_hist_item_os")
	private  int id;
	
	@ManyToOne
	@JoinColumn(name="hist_os_id")
	private HistoricoOrdemServico historicoOrdemServico;
	
	
	private int idOS ;
	
	private int idItemOS;
	
	@ManyToOne
	@JoinColumn(name="servico_id")
	private Servico servico;
			
	/*@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="hist_item_os_dente", joinColumns=
    {@JoinColumn(name="hist_item_os_id")}, inverseJoinColumns=
      {@JoinColumn(name="hist_dente_id")})
    private List<Dente> dentes;*/
	
	private String dentes;
	
	@ManyToOne
	@JoinColumn(name="cor_id")
	private Cor cor;
	
	private Long quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private String observacao;
	
	
	private Date dtCadastro;
	private Date dtAlteracao;
	private int idUser;
	private int idUserAlteracao;
	
	@Transient
	private String dentesLista;
	
	@Transient
	private List<String> listadentes;
	
	public List<String> getListadentes() {
		return listadentes;
	}
	public void setListadentes(List<String> listadentes) {
		List<String>  lista = new ArrayList<String>();
		for(String den : listadentes){
			
			if(!lista.contains(den)){
				lista.add(den);
			}
			
		}
		this.listadentes = lista;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
			
	public int getIdOS() {
		return idOS;
	}
	public void setIdOS(int idOS) {
		this.idOS = idOS;
	}
		
	public int getIdItemOS() {
		return idItemOS;
	}
	public void setIdItemOS(int idItemOS) {
		this.idItemOS = idItemOS;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public String getDentes() {
		return dentes;
	}
	public void setDentes(String dentes) {
		this.dentes = dentes;
	}
	public Cor getCor() {
		return cor;
	}
	public void setCor(Cor cor) {
		this.cor = cor;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
		if (obj instanceof ItemOrdemServico) {
			ItemOrdemServico itemOrdemServico = (ItemOrdemServico) obj;
			return itemOrdemServico.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return this.observacao;
	}
	public String getDentesLista() {
		return dentesLista;
	}
	public void setDentesLista(String dentesLista) {
		this.dentesLista = dentesLista;
	}
	public HistoricoOrdemServico getHistoricoOrdemServico() {
		return historicoOrdemServico;
	}
	public void setHistoricoOrdemServico(HistoricoOrdemServico historicoOrdemServico) {
		this.historicoOrdemServico = historicoOrdemServico;
	}
		
	
	
}
