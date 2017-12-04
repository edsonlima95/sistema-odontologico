package com.projetoodonto.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projetoodonto.dao.DentistaDAO;
import com.projetoodonto.model.Dentista;
import com.projetoodonto.util.jpa.Transactional;

public class ServiceDentista implements Serializable {

	@Inject
	private DentistaDAO dentistaDAO;
	
	@Transactional
	public void salvar(Dentista dentista) throws ServiceExecption {
		this.dentistaDAO.salvar(dentista);
	}
	
	@Transactional
	public void deletar(Long codigo) throws ServiceExecption {
		this.dentistaDAO.deletar(codigo);
	}
}
