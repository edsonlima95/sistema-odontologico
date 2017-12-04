package com.projetoodonto.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoodonto.dao.ConvenioDAO;
import com.projetoodonto.model.Convenio;
import com.projetoodonto.service.ServiceConvenio;
import com.projetoodonto.service.ServiceExecption;
import com.projetoodonto.util.jpa.Transactional;
import com.projetoodonto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroConvenioBean implements Serializable {

	private Convenio convenio = new Convenio();

	@Inject
	private ConvenioDAO convenioDAO;
	@Inject
	private ServiceConvenio serviceConvenio;

	public void salvar() {
		try {
			this.serviceConvenio.salvar(this.convenio);
			FacesUtil.addMessage(FacesMessage.SEVERITY_INFO,
					"O convênio " + this.convenio.getNome() + " foi cadastrado com sucesso!");
		} catch (ServiceExecption e) {
			FacesUtil.addMessage(FacesMessage.SEVERITY_WARN, e.getMessage());
		}
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

}
