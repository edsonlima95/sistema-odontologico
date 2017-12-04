package com.projetoodonto.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.sql.rowset.serial.SerialException;

import com.projetoodonto.dao.ConsultaDAO;
import com.projetoodonto.model.Cliente;
import com.projetoodonto.model.Consulta;
import com.projetoodonto.util.jpa.Transactional;

public class ServiceConsulta implements Serializable{

	@Inject
	private ConsultaDAO consultaDAO;
	
	@Transactional
	public void salvar(Consulta consulta) throws ServiceExecption {
		if(verificaConsulta(consulta.getDataConsulta(), consulta.getDentista().getCodigo())) {
			throw new ServiceExecption("O medico "+consulta.getDentista().getNome()+" já possui uma consulta agendada"
					+ " nessa data e horário, Verifique as datas disponiveis!");
		}
		
		this.consultaDAO.salvar(consulta);
	}
	
	public boolean verificaConsulta(Date data, Long codigo) {
		Consulta consuta = this.consultaDAO.verificaData(data, codigo);
		return consuta != null;
	}
	
	@Transactional
	public void deletar(Long codigo) throws ServiceExecption {
		this.consultaDAO.deletar(codigo);
	}
	
	
}
