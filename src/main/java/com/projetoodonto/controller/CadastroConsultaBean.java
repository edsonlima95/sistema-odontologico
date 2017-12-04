package com.projetoodonto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoodonto.dao.ClienteDAO;
import com.projetoodonto.dao.DentistaDAO;
import com.projetoodonto.model.Cliente;
import com.projetoodonto.model.Consulta;
import com.projetoodonto.model.Dentista;
import com.projetoodonto.model.TipoPagamento;
import com.projetoodonto.service.ServiceConsulta;
import com.projetoodonto.service.ServiceExecption;
import com.projetoodonto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroConsultaBean implements Serializable {

	@Inject
	private ServiceConsulta serviceConsulta;
	@Inject
	private ClienteDAO clienteDAO;
	@Inject
	private DentistaDAO dentistaDAO;

	private Consulta consulta = new Consulta();

	private List<Cliente> clientes = new ArrayList();
	private List<Dentista> dentistas = new ArrayList();
	private List<TipoPagamento> tiposPagamentos = new ArrayList();
	
	public void salvar() {
		try {
			this.serviceConsulta.salvar(consulta);
			FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, "A consulta foi cadastrado com sucesso!");
		} catch (ServiceExecption e) {
			FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR,e.getMessage());
		}
	}

	@PostConstruct
	public void init() {
		this.clientes = this.clienteDAO.buscarTodos();
		this.dentistas = this.dentistaDAO.buscarTodos();
		this.tiposPagamentos = Arrays.asList(TipoPagamento.values());
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<Dentista> getDentistas() {
		return dentistas;
	}

	public List<TipoPagamento> getTiposPagamentos() {
		return tiposPagamentos;
	}
	
	
}
