package com.projetoodonto.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value="cliente")//indica o valor da coluna tipo de pessoa na classe pessoa.
@NamedQueries({
	@NamedQuery(name="Cliente.buscarTodos", query="select c from Cliente c")
})
public class Cliente extends Pessoa {

	private List<Convenio> convenios = new ArrayList();
	private List<Consulta> consulta = new ArrayList();
		
	@ManyToMany
	@JoinTable(name="cliente_convenio",
				joinColumns=@JoinColumn(name="codigo_cliente"),
				inverseJoinColumns=@JoinColumn(name="codigo_convenio"))	
	public List<Convenio> getConvenios() {
		return convenios;
	}
	
	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}

	@OneToMany(mappedBy="cliente", cascade=CascadeType.REMOVE)
	public List<Consulta> getConsulta() {
		return consulta;
	}

	public void setConsulta(List<Consulta> consulta) {
		this.consulta = consulta;
	}

	
	
	
	
}
