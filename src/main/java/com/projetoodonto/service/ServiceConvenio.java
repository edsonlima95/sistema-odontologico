package com.projetoodonto.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.projetoodonto.dao.ConvenioDAO;
import com.projetoodonto.model.Convenio;
import com.projetoodonto.util.jpa.Transactional;

public class ServiceConvenio implements Serializable {

	@Inject
	private ConvenioDAO convenioDAO;

	@Transactional
	public void salvar(Convenio convenio) throws ServiceExecption {
		this.convenioDAO.salvar(convenio);
	}

	@Transactional
	public void deletar(Long codigo) throws ServiceExecption {

		if (this.verificaConvenioCliente(codigo)) {
			throw new ServiceExecption("O convênio contém um cliente, não pode ser apagado");
		}
		this.convenioDAO.deletar(codigo);
		
	}

	public boolean verificaConvenioCliente(Long codigo) {
		return this.convenioDAO.verificaConveio(codigo);
	}
}
