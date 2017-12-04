package com.projetoodonto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.projetoodonto.model.Cliente;
import com.projetoodonto.model.Dentista;

public class DentistaDAO implements Serializable {

	@Inject
	private EntityManager manager;	
	
	public void salvar(Dentista dentista) {
		this.manager.merge(dentista);
	}
	
	public List<Dentista> buscarTodos(){
		return this.manager.createQuery("select d from Dentista d order by d.nome asc").getResultList();
	}
	
	public Dentista buscarPorCodigo(Long codigo) {
		return this.manager.find(Dentista.class, codigo);
	}
	
	public void deletar(Long codigo) {
		Dentista dentista = this.buscarPorCodigo(codigo);
		this.manager.remove(dentista);
	}
	
	public List<Dentista> paginacao(int primeiro,int maximo){
		return this.manager.createQuery("select d from Dentista d", Dentista.class)
				.setFirstResult(primeiro)
				.setMaxResults(maximo)
				.getResultList();
	}

	public Long quantidade() {
		return this.manager.createQuery("select COUNT(d) from Dentista d", Long.class).getSingleResult();
	}
}
