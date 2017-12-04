package com.projetoodonto.lazyModels;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.projetoodonto.dao.ClienteDAO;
import com.projetoodonto.model.Cliente;

public class LazyModelCliente extends LazyDataModel<Cliente> implements Serializable {

	//Não injetar pois da nullpointer, tem receber pelo contrutor.
	private ClienteDAO clienteDAO;
	
	public LazyModelCliente(ClienteDAO clienteDAO) {
		super();
		this.clienteDAO = clienteDAO;
	}
	
	@Override
	public List<Cliente> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<Cliente> clientes = this.clienteDAO.paginacao(first, pageSize);
		this.setRowCount(this.clienteDAO.quantidade().intValue());
		return clientes;
	}
}
