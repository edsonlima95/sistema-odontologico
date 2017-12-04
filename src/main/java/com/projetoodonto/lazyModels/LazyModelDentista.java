package com.projetoodonto.lazyModels;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.projetoodonto.dao.ClienteDAO;
import com.projetoodonto.dao.DentistaDAO;
import com.projetoodonto.model.Cliente;
import com.projetoodonto.model.Dentista;

public class LazyModelDentista extends LazyDataModel<Dentista> implements Serializable {

	//Não injetar pois da nullpointer, tem receber pelo contrutor.
	private DentistaDAO dentistaDAO;
	
	public LazyModelDentista(DentistaDAO dentistaDAO) {
		super();
		this.dentistaDAO = dentistaDAO;
	}
	
	@Override
	public List<Dentista> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<Dentista> dentistas = this.dentistaDAO.paginacao(first, pageSize);
		this.setRowCount(this.dentistaDAO.quantidade().intValue());
		return dentistas;
	}
}
