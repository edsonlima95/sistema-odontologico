package com.projetoodonto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoodonto.dao.DentistaDAO;
import com.projetoodonto.lazyModels.LazyModelDentista;
import com.projetoodonto.model.Dentista;
import com.projetoodonto.service.ServiceDentista;
import com.projetoodonto.service.ServiceExecption;
import com.projetoodonto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaDentistaBean implements Serializable {

	@Inject
	private DentistaDAO dentistaDAO;
	@Inject
	private ServiceDentista serviceDentista;
	private LazyModelDentista lazyDentistas;
	private Dentista dentistaSelecionado = new Dentista();

	@PostConstruct
	public void init() {
		this.lazyDentistas = new LazyModelDentista(dentistaDAO);
	}

	public void deletar() {
		try {
			this.serviceDentista.deletar(this.dentistaSelecionado.getCodigo());
			FacesUtil.addMessage(FacesMessage.SEVERITY_INFO,
					"O dentista " + this.dentistaSelecionado.getNome() + " foi deletado com sucesso!");
	} catch (ServiceExecption e) {
			FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR,e.getMessage());
		}
	}
	
	public Long countDentistas() {
		return this.dentistaDAO.quantidade();
	}

	public LazyModelDentista getLazyDentistas() {
		return lazyDentistas;
	}
	
	public Dentista getDentistaSelecionado() {
		return dentistaSelecionado;
	}

	public void setDentistaSelecionado(Dentista dentistaSelecionado) {
		this.dentistaSelecionado = dentistaSelecionado;
	}	
	
}
