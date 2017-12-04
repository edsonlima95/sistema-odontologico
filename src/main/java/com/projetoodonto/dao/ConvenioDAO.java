package com.projetoodonto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.projetoodonto.model.Convenio;

public class ConvenioDAO implements Serializable {

	@Inject
	private EntityManager manager;
	
	
	public void salvar(Convenio convenio) {
		this.manager.merge(convenio);
	}

	public List<Convenio> buscarTodos(){
		return this.manager.createNamedQuery("Convenio.buscartodos").getResultList();
	}

	public Convenio buscarPorCodigo(Long codigo) {
		return this.manager.find(Convenio.class, codigo);
	}
	
	public void deletar(Long codigo) {
		Convenio convenio = this.buscarPorCodigo(codigo);
		this.manager.remove(convenio);
	}
	
	//Verifica se o convenio tem relação com o cliente, se sim nao pode ser apagado.
	public boolean verificaConveio(Long codigo) {
		try {
			this.manager.createNativeQuery("select * from cliente_convenio cc where cc.codigo_convenio = :codigo")
			.setParameter("codigo", codigo)
			.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		} 
		
	}

	public List<Convenio> paginacao(int first, int pageSize) {
		return this.manager.createQuery("select c from Convenio c",Convenio.class)
										.setFirstResult(first)
										.setMaxResults(pageSize)
										.getResultList();
	}

	public Long quantidade() {
		return this.manager.createQuery("select count(c) from Convenio c",Long.class).getSingleResult();
	}

}
