package com.projetoodonto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.projetoodonto.dao.ClienteDAO;
import com.projetoodonto.dao.ConvenioDAO;
import com.projetoodonto.model.Cliente;
import com.projetoodonto.model.Convenio;

//Não sei pq mas quando e para uma lista em um select tem que definir o valor.
@FacesConverter(forClass=Cliente.class)
public class ClienteConverter implements Converter {

	@Inject
	private ClienteDAO clienteDAO;
	
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente cliente = null;
		
		if(value != null) {
			return cliente = this.clienteDAO.buscarPorCodigo(Long.parseLong(value));
		}
		
		return cliente;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Long codigo = ((Cliente) value).getCodigo();//Faz o cast de objeto para long.
			return codigo == null ? null : codigo.toString();//Retorna o codigo
		}
		return null;
	}

}
