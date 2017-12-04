package com.projetoodonto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class TesteDAO implements Serializable {

	@Inject
	private EntityManager manager;
	
	public List<String> buscar(Long codigo){
		return this.manager.createQuery("select c.nome from Cidade c "
				+ "inner join Estado e on c.codigo_estado = e.codigo where e.codigo = :codigo ")
				.setParameter("codigo",codigo)
				.getResultList();
	}
	
}
