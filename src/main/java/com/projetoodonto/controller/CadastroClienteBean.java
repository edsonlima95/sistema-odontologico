package com.projetoodonto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoodonto.dao.CidadeDAO;
import com.projetoodonto.dao.ConvenioDAO;
import com.projetoodonto.dao.EstadoDAO;
import com.projetoodonto.model.Cidade;
import com.projetoodonto.model.Cliente;
import com.projetoodonto.model.Convenio;
import com.projetoodonto.model.Estado;
import com.projetoodonto.service.ServiceCliente;
import com.projetoodonto.service.ServiceExecption;
import com.projetoodonto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	@Inject
	private ServiceCliente serviceCliente;
	@Inject
	private ConvenioDAO convenioDAO;
	@Inject
	private EstadoDAO estadoDAO;
	@Inject
	private CidadeDAO cidadeDAO;

	private Cliente cliente = new Cliente();
	private List<Convenio> convenios = new ArrayList();
	private List<Estado> estados = new ArrayList();
	private List<Cidade> cidades = new ArrayList();

	@PostConstruct
	public void init() {
		this.convenios = this.convenioDAO.buscarTodos();
		this.estados = this.estadoDAO.buscarTodos();
	}

	public void onSelect() {
		this.cidades = this.cidadeDAO.buscarCidades(cliente.getEstado().getCodigo());
	}

	public void salvar() {
		try {
			this.serviceCliente.salvar(cliente);
			FacesUtil.addMessage(FacesMessage.SEVERITY_INFO,
					"O cliente " + this.cliente.getNome() + " foi cadastrado com sucesso!");
		} catch (ServiceExecption e) {
			FacesUtil.addMessage(FacesMessage.SEVERITY_INFO,e.getMessage());
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Convenio> getConvenios() {
		return convenios;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

}
