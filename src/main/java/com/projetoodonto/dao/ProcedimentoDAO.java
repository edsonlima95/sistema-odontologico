package com.projetoodonto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.projetoodonto.model.Dente;
import com.projetoodonto.model.Procedimento;

public class ProcedimentoDAO implements Serializable{
	
	@Inject
	private EntityManager manager;
	
	public List<Procedimento> buscarTodos(){
		return this.manager.createQuery("select p from Procedimento p",Procedimento.class).getResultList();
	}

	public Procedimento buscarPorCodigo(Long codigo) {
		return this.manager.find(Procedimento.class,codigo);
	}
	
	
	
}
