package com.projetoodonto.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.projetoodonto.model.Cliente;
import com.projetoodonto.model.Convenio;
import com.projetoodonto.model.Pessoa;

public class ClienteDAO implements Serializable {

	@Inject
	private EntityManager manager;	
	
	public void salvar(Cliente cliente) {
		this.manager.merge(cliente);
	}
	
	public List<Cliente> buscarTodos(){
		return this.manager.createNamedQuery("Cliente.buscarTodos",Cliente.class).getResultList();
	}
	
	
	public List<Cliente> clienteodontograma(){
		return this.manager.createQuery("select c from Odontograma o inner join o.cliente c",Cliente.class).getResultList();
	}
	
	public Cliente buscarClienteConvenios(Long codigo) {
		return this.manager.createQuery("select c from Cliente c left join fetch c.convenios where c.codigo = :codigo",Cliente.class)
				.setParameter("codigo",codigo)
				.getSingleResult();
	}
	
	public Cliente buscarPorCodigo(Long codigo) {
		return this.manager.find(Cliente.class, codigo);
	}
	
	public void delete(Long codigo) {
		Cliente cliente = this.buscarPorCodigo(codigo);
		this.manager.remove(cliente);
	}

	public List<Cliente> paginacao(int primeiro,int maximo){
		return this.manager.createNamedQuery("Cliente.buscarTodos", Cliente.class)
				.setFirstResult(primeiro)
				.setMaxResults(maximo)
				.getResultList();
	}

	public Long quantidade() {
		return this.manager.createQuery("select COUNT(c) from Cliente c", Long.class).getSingleResult();
	}

}
