package com.projetoodonto.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoodonto.model.Funcionario;
import com.projetoodonto.service.ServiceExecption;
import com.projetoodonto.service.ServiceFuncionario;
import com.projetoodonto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFuncionarioBean implements Serializable{

	private Funcionario funcionario = new Funcionario();
	@Inject
	private ServiceFuncionario serviceFuncionario;
	private Funcionario funcionarioSelecionada;
	
	public void salvar() {
		try {
			this.serviceFuncionario.salvar(funcionario);
			FacesUtil.addMessage(FacesMessage.SEVERITY_INFO,
					"O funcionário "+ this.funcionario.getNome() + " foi cadastrado com sucesso!");
		} catch (ServiceExecption e) {
			FacesUtil.addMessage(FacesMessage.SEVERITY_INFO,e.getMessage());
		}
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Funcionario getFuncionarioSelecionada() {
		return funcionarioSelecionada;
	}
	public void setFuncionarioSelecionada(Funcionario funcionarioSelecionada) {
		this.funcionarioSelecionada = funcionarioSelecionada;
	}
	
	
	
}
