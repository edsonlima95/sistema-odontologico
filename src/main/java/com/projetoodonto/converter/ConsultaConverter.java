package com.projetoodonto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.projetoodonto.dao.ConsultaDAO;
import com.projetoodonto.model.Consulta;

@FacesConverter(forClass=Consulta.class)
public class ConsultaConverter implements Converter{

	@Inject
	public ConsultaDAO consultaDAO;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Consulta consulta = null;
		
		if(value != null) {
			return consulta = this.consultaDAO.buscarPorCodigo(Long.parseLong(value));
		}
		return consulta;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Long codigo = ((Consulta) value).getCodigo();
			return codigo == null ? null : codigo.toString();
		}
		return null;
	}

}
