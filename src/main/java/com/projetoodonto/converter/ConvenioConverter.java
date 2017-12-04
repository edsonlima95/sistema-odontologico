package com.projetoodonto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.projetoodonto.dao.ConvenioDAO;
import com.projetoodonto.model.Convenio;

//Não sei pq mas quando e para uma lista em um select tem que definir o valor.
@FacesConverter(value="convenioConverter")
public class ConvenioConverter implements Converter {

	@Inject
	private ConvenioDAO convenioDAO;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Convenio convenio = null;
		
		if(value != null) {
			return convenio = this.convenioDAO.buscarPorCodigo(Long.parseLong(value));
		}
		
		return convenio;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Long codigo = ((Convenio) value).getCodigo();//Faz o cast de objeto para long.
			return codigo == null ? null : codigo.toString();//Retorna o codigo
		}
		return null;
	}

}
