package com.projetoodonto.dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.projetoodonto.model.Dente;
import com.projetoodonto.util.jsf.FacesUtil;

public class DenteDAO implements Serializable {

	@Inject
	private EntityManager manager;
	
	public void salvar(Dente dente) {
		this.manager.merge(dente);
	}
	
	public List<Dente> buscarTodos(){
		return this.manager.createQuery("select d from Dente d",Dente.class).getResultList();
	}
	
	public Dente buscarPorCodigo(Long codigo) {
		return this.manager.find(Dente.class, codigo);
	}
	
	public void deletar(Long codigo) {
		Dente dente = this.buscarPorCodigo(codigo);
		this.manager.remove(dente);
	}
	
	public Dente buscarDentesProcedimentos(Long codigo) {
		return this.manager.createQuery("select d from Dente d left join fetch d.procedimentos where d.codigo = :codigo",Dente.class)
				.setParameter("codigo", codigo)
				.getSingleResult();
	}
}
