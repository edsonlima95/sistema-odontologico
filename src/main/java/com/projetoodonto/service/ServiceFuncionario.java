package com.projetoodonto.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projetoodonto.dao.FuncionarioDAO;
import com.projetoodonto.model.Funcionario;
import com.projetoodonto.util.jpa.Transactional;

public class ServiceFuncionario implements Serializable {

	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	@Transactional
	public void salvar(Funcionario funcionario) throws ServiceExecption{
		this.funcionarioDAO.salvar(funcionario);
	}
	
	@Transactional
	public void deletar(Long codigo) throws ServiceExecption{
		this.funcionarioDAO.deletar(codigo);
	}
	
}
