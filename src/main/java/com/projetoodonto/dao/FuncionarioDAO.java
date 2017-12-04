package com.projetoodonto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.projetoodonto.model.Funcionario;

public class FuncionarioDAO implements Serializable {

	@Inject
	private EntityManager manager;
	
	public void salvar(Funcionario funcionario) {
		this.manager.merge(funcionario);
	}

	public List<Funcionario> paginacao(int first, int pageSize) {
		return this.manager.createQuery("select f from Funcionario f",Funcionario.class)
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
	}
	
	public Long quantidade() {
		return this.manager.createQuery("select count(f) from Funcionario f",Long.class).getSingleResult();
	}
	
	public void deletar(Long codigo) {
		Funcionario funcionario = this.buscarPorCodigo(codigo);
		this.manager.remove(funcionario);
	}

	public Funcionario buscarPorCodigo(Long codigo) {
		return this.manager.find(Funcionario.class, codigo);
	}
	
}
