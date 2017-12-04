package com.projetoodonto.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.projetoodonto.dao.ClienteDAO;
import com.projetoodonto.model.Cliente;
import com.projetoodonto.util.jpa.Transactional;

public class ServiceCliente implements Serializable{

	@Inject
	private ClienteDAO clienteDAO;
	
	@Transactional
	public void salvar(Cliente cliente) throws ServiceExecption{
		this.clienteDAO.salvar(cliente);
	}
	
	@Transactional
	public void delete(Long codigo) throws ServiceExecption {
		this.clienteDAO.delete(codigo);
	}
}
