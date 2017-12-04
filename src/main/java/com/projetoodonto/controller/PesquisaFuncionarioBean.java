package com.projetoodonto.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoodonto.dao.FuncionarioDAO;
import com.projetoodonto.lazyModels.LazyModelFuncionario;
import com.projetoodonto.model.Funcionario;
import com.projetoodonto.service.ServiceExecption;
import com.projetoodonto.service.ServiceFuncionario;
import com.projetoodonto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaFuncionarioBean implements Serializable {

	private Funcionario funcionario = new Funcionario();
	private LazyModelFuncionario lazyFuncionarios;
	private Funcionario funcionarioSelecionado;
	@Inject
	private FuncionarioDAO funcionarioDAO;
	@Inject
	private ServiceFuncionario serviceFuncionario;
	
	@PostConstruct
	public void init() {
		this.lazyFuncionarios = new LazyModelFuncionario(funcionarioDAO);
	}
	
	public void deletar() {
		try {
			this.serviceFuncionario.deletar(this.funcionarioSelecionado.getCodigo());
			FacesUtil.addMessage(FacesMessage.SEVERITY_INFO,"O funcionario "+
									this.funcionarioSelecionado.getNome()+" foi cadastrado com sucesso!");
		} catch (ServiceExecption e) {
			FacesUtil.addMessage(FacesMessage.SEVERITY_ERROR,e.getMessage());
		}
	}
	
	public Long countFunc() {
		return this.funcionarioDAO.quantidade();
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}
	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}
	public LazyModelFuncionario getLazyFuncionarios() {
		return lazyFuncionarios;
	}
	
	
	
}
