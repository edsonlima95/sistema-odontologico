package com.projetoodonto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.projetoodonto.dao.ClienteDAO;
import com.projetoodonto.dao.DenteDAO;
import com.projetoodonto.model.Cliente;
import com.projetoodonto.model.Dente;

@FacesConverter(forClass=Dente.class)
public class DenteConverter implements Converter{
	@Inject
	private DenteDAO denteDAO;
	
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Dente dente = null;
		
		if(value != null) {
			return dente = this.denteDAO.buscarPorCodigo(Long.parseLong(value));
		}
		
		return dente;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Long codigo = ((Dente) value).getCodigo();//Faz o cast de objeto para long.
			return codigo == null ? null : codigo.toString();//Retorna o codigo
		}
		return null;
	}
}
