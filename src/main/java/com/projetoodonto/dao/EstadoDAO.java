package com.projetoodonto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.projetoodonto.model.Estado;

public class EstadoDAO implements Serializable{

	@Inject
	private EntityManager manager;
	
	public List<Estado> buscarTodos(){
		return this.manager.createQuery("select e from Estado e").getResultList();
	}

	public Estado buscarPorCodigo(Long codigo) {
		return this.manager.find(Estado.class, codigo);
	}
	
}
