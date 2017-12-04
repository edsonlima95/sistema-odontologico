package com.projetoodonto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoodonto.dao.ClienteDAO;
import com.projetoodonto.lazyModels.LazyModelCliente;
import com.projetoodonto.model.Cliente;
import com.projetoodonto.service.ServiceCliente;
import com.projetoodonto.service.ServiceExecption;
import com.projetoodonto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaClienteBean implements Serializable{

	private Cliente clineteSelecionada;
	private Cliente clienteConvenios;
	private LazyModelCliente lazyClientes;
	
	@Inject
	private ClienteDAO clienteDAO;
	
	@Inject
	private ServiceCliente serviceCliente;
	
	@PostConstruct
	public void init() {
		this.lazyClientes = new LazyModelCliente(clienteDAO);
	}
	
	public void delete() {
		try {
			this.serviceCliente.delete(this.clineteSelecionada.getCodigo());
			FacesUtil.addMessage(FacesMessage.SEVERITY_INFO,
					"O cliente " + this.clineteSelecionada.getNome() + " foi deletado com sucesso!");
		} catch (ServiceExecption e) {
			FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR,e.getMessage());
		}
	}
	
	public void buscarConvenios() {
		this.clienteConvenios = this.clienteDAO.buscarClienteConvenios(clienteConvenios.getCodigo());
	}
	
	public Long countUsuarios() {
		return this.clienteDAO.quantidade();
	}

	public Cliente getClineteSelecionada() {
		return clineteSelecionada;
	}

	public void setClineteSelecionada(Cliente clineteSelecionada) {
		this.clineteSelecionada = clineteSelecionada;
	}

	public Cliente getClienteConvenios() {
		return clienteConvenios;
	}

	public void setClienteConvenios(Cliente clienteConvenios) {
		this.clienteConvenios = clienteConvenios;
	}

	public LazyModelCliente getLazyClientes() {
		return lazyClientes;
	}
	
	
	
	
}
