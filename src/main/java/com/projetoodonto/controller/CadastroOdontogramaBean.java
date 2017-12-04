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
import com.projetoodonto.dao.DentistaDAO;
import com.projetoodonto.dao.OdontogramaDAO;
import com.projetoodonto.model.Cliente;
import com.projetoodonto.model.Dente;
import com.projetoodonto.model.Dentista;
import com.projetoodonto.model.Odontograma;
import com.projetoodonto.service.ServiceExecption;
import com.projetoodonto.service.ServiceOdontograma;
import com.projetoodonto.util.jpa.Transactional;
import com.projetoodonto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroOdontogramaBean implements Serializable {

	private Odontograma odontograma = new Odontograma();
	private List<Dente> dentes = new ArrayList();
	private List<Cliente> clientes = new ArrayList();
	private List<Dentista> dentistas = new ArrayList();
	/* Faz a injeção do bean do dente. */
	@Inject
	private CadastroDenteBean cadastroDenteBean;
	@Inject
	private ServiceOdontograma serviceOdondograma;
	@Inject
	private OdontogramaDAO odontogramaDAO;
	@Inject
	private ClienteDAO clienteDAO;
	@Inject
	private DentistaDAO dentistaDAO;

	@PostConstruct
	public void init() {
		this.clientes = clienteDAO.buscarTodos();
		this.dentistas = dentistaDAO.buscarTodos();
	}

	public void add() {
		// Seta o objeto dente vindo da injeção na lista de arrays.
		this.odontograma.getDentes().add(this.cadastroDenteBean.getDente());// ADD
		// Diciona a lista de dentes em dentes que será exibido na tabela.
		this.dentes = this.odontograma.getDentes();// SETA.
		FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, "Dente inserido");
	}

	public void salvar() {
		try {
			this.serviceOdondograma.salvar(odontograma);
			FacesUtil.addMessage(FacesMessage.SEVERITY_INFO, "O odontograma foi cadastrado com sucesso!");
			/*
			 * Limpa a lista de dentes, se não o ultimo valor inserido é novamente inserido
			 * junto com o novo assim ficando duplicado..
			 */
			this.odontograma.setDentes(new ArrayList());
		} catch (ServiceExecption e) {
			FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
	}

	public Odontograma getOdontograma() {
		return odontograma;
	}

	public void setOdontograma(Odontograma odontograma) {
		this.odontograma = odontograma;
	}

	public List<Dente> getDentes() {
		return dentes;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<Dentista> getDentistas() {
		return dentistas;
	}

}
