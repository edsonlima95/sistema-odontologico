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
import com.projetoodonto.dao.DenteDAO;
import com.projetoodonto.dao.OdontogramaDAO;
import com.projetoodonto.model.Cliente;
import com.projetoodonto.model.Dente;
import com.projetoodonto.model.Odontograma;
import com.projetoodonto.util.jpa.Transactional;
import com.projetoodonto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaOdontogramaBean implements Serializable {

	@Inject
	private OdontogramaDAO odontogramaDAO;
	@Inject
	private ClienteDAO clienteDAO;
	@Inject
	private DenteDAO denteDAO;
	private Odontograma odontograma = new Odontograma();
	private List<Odontograma> odontogramas = new ArrayList();
	private List<Cliente> clientes = new ArrayList();
	private Odontograma odontogramaSelecionado;
	private Cliente clienteSelecionado;
	private Dente denteSelecionado;
	private Odontograma buscarDentesSelecionado;
	private Dente denteProcedimentosSelecionado;
	
	@PostConstruct
	public void init() {
		this.clientes = this.clienteDAO.clienteodontograma();
	}

	public void buscarPorCliente() {
		this.odontogramas = this.odontogramaDAO.buscarPorCliente(clienteSelecionado);
	}

	@Transactional
	public void deletarDente() {
		this.denteDAO.deletar(this.denteSelecionado.getCodigo());
		FacesUtil.addMessage(FacesMessage.SEVERITY_INFO,"Dente deletado com sucesso");
		this.odontograma.getDentes().remove(denteSelecionado);
	}
	
	@Transactional
	public void deletarOdontograma() {
		this.odontogramaDAO.deletarOdontograma(this.odontogramaSelecionado.getCodigo());
		FacesUtil.addMessage(FacesMessage.SEVERITY_INFO,"Odontofgrama deletado com sucesso");
		this.odontogramas.remove(odontogramaSelecionado);
	}
	
	public void buscarDentes() {
		System.out.println(this.buscarDentesSelecionado.getCodigo());
		this.buscarDentesSelecionado = this.odontogramaDAO.buscarOdontoDentes(this.buscarDentesSelecionado.getCodigo());
	}
	
	public void buscarDenteProcedimento() {
		this.denteProcedimentosSelecionado = 
				this.denteDAO.buscarDentesProcedimentos(this.denteProcedimentosSelecionado.getCodigo());
	}
	
	public Odontograma getOdontograma() {
		return odontograma;
	}

	public void setOdontograma(Odontograma odontograma) {
		this.odontograma = odontograma;
	}

	public Odontograma getOdontogramaSelecionado() {
		return odontogramaSelecionado;
	}

	public void setOdontogramaSelecionado(Odontograma odontogramaSelecionado) {
		this.odontogramaSelecionado = odontogramaSelecionado;
	}

	public List<Odontograma> getOdontogramas() {
		return odontogramas;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public Dente getDenteSelecionado() {
		return denteSelecionado;
	}

	public void setDenteSelecionado(Dente denteSelecionado) {
		this.denteSelecionado = denteSelecionado;
	}

	public Odontograma getBuscarDentesSelecionado() {
		return buscarDentesSelecionado;
	}

	public void setBuscarDentesSelecionado(Odontograma buscarDentesSelecionado) {
		this.buscarDentesSelecionado = buscarDentesSelecionado;
	}

	public Dente getDenteProcedimentosSelecionado() {
		return denteProcedimentosSelecionado;
	}

	public void setDenteProcedimentosSelecionado(Dente denteProcedimentosSelecionado) {
		this.denteProcedimentosSelecionado = denteProcedimentosSelecionado;
	}

	

}
