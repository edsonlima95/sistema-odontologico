package com.projetoodonto.lazyModels;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.projetoodonto.dao.FuncionarioDAO;
import com.projetoodonto.model.Funcionario;

public class LazyModelFuncionario extends LazyDataModel<Funcionario> implements Serializable {

	private FuncionarioDAO funcionarioDAO;

	public LazyModelFuncionario(FuncionarioDAO funcionarioDAO) {
		super();
		this.funcionarioDAO = funcionarioDAO;
	}
	
	@Override
	public List<Funcionario> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<Funcionario> funcionarios = this.funcionarioDAO.paginacao(first, pageSize);
		setRowCount(this.funcionarioDAO.quantidade().intValue());
		return funcionarios;
	}
	
}
