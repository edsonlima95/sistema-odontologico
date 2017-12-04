package com.projetoodonto.lazyModels;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.projetoodonto.dao.ClienteDAO;
import com.projetoodonto.dao.ConvenioDAO;
import com.projetoodonto.model.Convenio;

public class LazyModelConvenio extends LazyDataModel<Convenio> implements Serializable {

	//Não injetar pois da nullpointer, tem receber pelo contrutor.
	private ConvenioDAO convenioDAO;
	
	public LazyModelConvenio(ConvenioDAO convenioDAO) {
		super();
		this.convenioDAO = convenioDAO;
	}
	
	@Override
	public List<Convenio> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<Convenio> convenios = this.convenioDAO.paginacao(first, pageSize);
		this.setRowCount(this.convenioDAO.quantidade().intValue());
		return convenios;
	}
}
