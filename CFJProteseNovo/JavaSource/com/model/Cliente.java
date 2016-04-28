package com.model;

import java.io.Serializable;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;




@Entity
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name="seq_cliente",sequenceName="seq_cliente",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_cliente")
	private int id;
	private String name;
	private String endereco;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
	 @OneToMany(mappedBy = "cliente", targetEntity = OrdemServico.class, fetch = FetchType.LAZY)
	 private List<OrdemServico> ordemServico;
	 
	 
	public List<OrdemServico> getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(List<OrdemServico> ordemServico) {
		this.ordemServico = ordemServico;
	}

	@OneToMany(mappedBy = "cliente", targetEntity = Dentista.class, fetch = FetchType.LAZY)
	 private List<Dentista> dentista;
	 
	 
	public List<Dentista> getDentista() {
		return dentista;
	}

	public void setDentista(List<Dentista> dentista) {
		this.dentista = dentista;
	}

	@OneToMany(mappedBy = "cliente", targetEntity = HistoricoOrdemServico.class, fetch = FetchType.LAZY)
	private List<HistoricoOrdemServico> historicoOrdemServico;
	
	public List<HistoricoOrdemServico> getHistoricoOrdemServico() {
		return historicoOrdemServico;
	}

	public void setHistoricoOrdemServico(
			List<HistoricoOrdemServico> historicoOrdemServico) {
		this.historicoOrdemServico = historicoOrdemServico;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Cliente) {
			Cliente cliente = (Cliente) obj;
			return cliente.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
