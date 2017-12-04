package com.projetoodonto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.projetoodonto.model.Cidade;

public class CidadeDAO implements Serializable {

	@Inject
	private EntityManager manager;

	public List<Cidade> buscarCidades(Long codigo) {
		String query = "select c from Cidade c inner join Estado e on c.codigo_estado = e.codigo where e.codigo = :codigo";
		return this.manager.createQuery(query,Cidade.class).setParameter("codigo", codigo).getResultList();
	}

	public Cidade buscarPorCodigo(Long codigo) {
		return this.manager.find(Cidade.class, codigo);
	}

}
