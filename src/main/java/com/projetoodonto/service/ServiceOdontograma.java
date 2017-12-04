package com.projetoodonto.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projetoodonto.dao.OdontogramaDAO;
import com.projetoodonto.model.Odontograma;
import com.projetoodonto.util.jpa.Transactional;

public class ServiceOdontograma implements Serializable {

	@Inject
	private OdontogramaDAO odontogramaDAO;
	
	@Transactional
	public void salvar(Odontograma odontograma) throws ServiceExecption {
		this.odontogramaDAO.salvar(odontograma);
	}
	
}
