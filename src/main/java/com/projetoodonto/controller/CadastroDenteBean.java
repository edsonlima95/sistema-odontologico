package com.projetoodonto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoodonto.dao.DenteDAO;
import com.projetoodonto.dao.ProcedimentoDAO;
import com.projetoodonto.model.Dente;
import com.projetoodonto.model.Procedimento;
import com.projetoodonto.util.jpa.Transactional;
import com.projetoodonto.util.jsf.FacesUtil;

/*Não contem metodos de inserção pq sera inserido junto com o odontograma
 * ou seja cascade.*/

@Named
@RequestScoped
public class CadastroDenteBean implements Serializable {

	@Inject
	private DenteDAO denteDAO;
	@Inject
	private ProcedimentoDAO procedimentoDAO;
	
	private Dente dente = new Dente();
	private List<Procedimento> procedimentos = new ArrayList();
	private List<Procedimento> procedimentoSelecionados = new ArrayList();
	private Dente procedimentoSelecionado;

	@PostConstruct
	public void init() {
		this.procedimentos = this.procedimentoDAO.buscarTodos();
	}
	
	public void listaProcedimentos(){
		System.out.println(this.procedimentoSelecionado.getCodigo());
	}
	
	@Transactional
	public void salvar() {
		this.denteDAO.salvar(dente);
		FacesUtil.addMessage(FacesMessage.SEVERITY_INFO,
				"O dente foi cadastrado com sucesso!");
		}
	
	public Dente getDente() {
		return dente;
	}

	public void setDente(Dente dente) {
		this.dente = dente;
	}

	public List<Procedimento> getProcedimentos() {
		return procedimentos;
	}

	public List<Procedimento> getProcedimentoSelecionados() {
		return procedimentoSelecionados;
	}

	public Dente getProcedimentoSelecionado() {
		return procedimentoSelecionado;
	}

	public void setProcedimentoSelecionado(Dente procedimentoSelecionado) {
		this.procedimentoSelecionado = procedimentoSelecionado;
	}

	
}
