package com.projetoodonto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.projetoodonto.dao.DentistaDAO;
import com.projetoodonto.model.Dentista;

//Não sei pq mas quando e para uma lista em um select tem que definir o valor.
@FacesConverter(forClass=Dentista.class)
public class DentistaConverter implements Converter {

	@Inject
	private DentistaDAO dentistaDAO;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Dentista dentista = null;
		
		if(value != null) {
			return dentista = this.dentistaDAO.buscarPorCodigo(Long.parseLong(value));
		}
		
		return dentista;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Long codigo = ((Dentista) value).getCodigo();//Faz o cast de objeto para long.
			return codigo == null ? null : codigo.toString();//Retorna o codigo
		}
		return null;
	}

}
