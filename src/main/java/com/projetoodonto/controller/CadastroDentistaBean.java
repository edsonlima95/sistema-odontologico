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
import com.projetoodonto.dao.DentistaDAO;
import com.projetoodonto.dao.EstadoDAO;
import com.projetoodonto.model.Cidade;
import com.projetoodonto.model.Convenio;
import com.projetoodonto.model.Dentista;
import com.projetoodonto.model.Estado;
import com.projetoodonto.service.ServiceDentista;
import com.projetoodonto.service.ServiceExecption;
import com.projetoodonto.util.jpa.Transactional;
import com.projetoodonto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroDentistaBean implements Serializable {

	@Inject
	private EstadoDAO estadoDAO;
	@Inject
	private CidadeDAO cidadeDAO;
	@Inject
	private ServiceDentista serviceDentista;
	private Dentista dentista = new Dentista();
	private List<Estado> estados = new ArrayList();
	private List<Cidade> cidades = new ArrayList();

	@PostConstruct
	public void init() {
		this.estados = this.estadoDAO.buscarTodos();
	}

	public void onSelect() {
		this.cidades = this.cidadeDAO.buscarCidades(dentista.getEstado().getCodigo());
	}

	public void salvar() {
		try {
			this.serviceDentista.salvar(dentista);
			FacesUtil.addMessage(FacesMessage.SEVERITY_INFO,
					"O dentista " + this.dentista.getNome() + " foi cadastrado com sucesso!");
		} catch (ServiceExecption e) {
			FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
	}

	public Dentista getDentista() {
		return dentista;
	}

	public void setDentista(Dentista dentista) {
		this.dentista = dentista;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

}
