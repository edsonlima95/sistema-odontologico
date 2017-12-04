package com.projetoodonto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoodonto.dao.ConvenioDAO;
import com.projetoodonto.lazyModels.LazyModelConvenio;
import com.projetoodonto.model.Convenio;
import com.projetoodonto.service.ServiceConvenio;
import com.projetoodonto.service.ServiceExecption;
import com.projetoodonto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaConvenioBean implements Serializable {

	@Inject
	private ConvenioDAO convenioDAO;
	@Inject
	private ServiceConvenio serviceConvenio;
	private Convenio convenio = new Convenio();
	private Convenio convenioSelecionado = new Convenio();
	private LazyModelConvenio lazyConvenios;

	@PostConstruct
	public void init() {
		this.lazyConvenios = new LazyModelConvenio(convenioDAO);
	}

	public void deletar() {
		try {
			this.serviceConvenio.deletar(this.convenioSelecionado.getCodigo());
			
			FacesUtil.addMessage(FacesMessage.SEVERITY_INFO,
					"O conv�nio " + this.convenioSelecionado.getNome() + " foi deletado com sucesso!");
		} catch (ServiceExecption e) {
			FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR,e.getMessage());
		}
	}
	
	public Long countConvenios() {
		return this.convenioDAO.quantidade();
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public Convenio getConvenioSelecionado() {
		return convenioSelecionado;
	}

	public void setConvenioSelecionado(Convenio convenioSelecionado) {
		this.convenioSelecionado = convenioSelecionado;
	}

	public LazyModelConvenio getLazyConvenios() {
		return lazyConvenios;
	}
	
	
}
