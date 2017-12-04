package com.projetoodonto.lazyModels;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.projetoodonto.dao.ClienteDAO;
import com.projetoodonto.dao.ConsultaDAO;
import com.projetoodonto.model.Cliente;
import com.projetoodonto.model.Consulta;

public class LazyModelConsulta extends LazyDataModel<Consulta> implements Serializable {

	//Não injetar pois da nullpointer, tem receber pelo contrutor.
	private ConsultaDAO consultaDAO;
	
	public LazyModelConsulta(ConsultaDAO consultaDAO) {
		super();
		this.consultaDAO = consultaDAO;
	}
	
	@Override
	public List<Consulta> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<Consulta> consultas = this.consultaDAO.paginacao(first, pageSize);
		this.setRowCount(this.consultaDAO.quantidade().intValue());
		return consultas;
	}
}
