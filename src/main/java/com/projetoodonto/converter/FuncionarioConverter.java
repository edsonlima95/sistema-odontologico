package com.projetoodonto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.projetoodonto.dao.FuncionarioDAO;
import com.projetoodonto.model.Funcionario;

//Não sei pq mas quando e para uma lista em um select tem que definir o valor.
@FacesConverter(forClass=Funcionario.class)
public class FuncionarioConverter implements Converter {

	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Funcionario funcionario = null;
		
		if(value != null) {
			return funcionario = this.funcionarioDAO.buscarPorCodigo(Long.parseLong(value));
		}
		
		return funcionario;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Long codigo = ((Funcionario) value).getCodigo();//Faz o cast de objeto para long.
			return codigo == null ? null : codigo.toString();//Retorna o codigo
		}
		return null;
	}

}
