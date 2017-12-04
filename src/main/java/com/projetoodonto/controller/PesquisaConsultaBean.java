package com.projetoodonto.controller;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoodonto.dao.ConsultaDAO;
import com.projetoodonto.lazyModels.LazyModelConsulta;
import com.projetoodonto.model.Consulta;
import com.projetoodonto.service.ServiceConsulta;
import com.projetoodonto.service.ServiceExecption;
import com.projetoodonto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaConsultaBean implements Serializable {

	@Inject
	private ConsultaDAO consultaDAO;
	@Inject
	private ServiceConsulta serviceConsulta;
	private Consulta consulta = new Consulta();
	private LazyModelConsulta lazyConsultas;
	private Consulta consultaSelecionada = new Consulta();
	
	@PostConstruct
	public void init() {
		this.lazyConsultas = new LazyModelConsulta(consultaDAO);
	}

	public void deletar() {
		try {
			this.serviceConsulta.deletar(this.consultaSelecionada.getCodigo());
			FacesUtil.addMessage(FacesMessage.SEVERITY_INFO,
					"A consulta foi deletada com sucesso!");
			
		} catch (ServiceExecption e) {
			FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR,e.getMessage());
		}
	}
	
	public BigDecimal valorTotalMes() {
		return this.consultaDAO.somaValorMes();
	}
	
	public Long countConsultas() {
		return this.consultaDAO.quantidade();
	}

	public LazyModelConsulta getLazyConsultas() {
		return lazyConsultas;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Consulta getConsultaSelecionada() {
		return consultaSelecionada;
	}

	public void setConsultaSelecionada(Consulta consultaSelecionada) {
		this.consultaSelecionada = consultaSelecionada;
	}
}
