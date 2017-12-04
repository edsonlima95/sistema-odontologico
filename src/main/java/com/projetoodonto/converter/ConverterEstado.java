package com.projetoodonto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.projetoodonto.dao.EstadoDAO;
import com.projetoodonto.model.Estado;

@FacesConverter(value="estadoConverter")
public class ConverterEstado implements Converter{

	@Inject
	private EstadoDAO estadoDAO;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Estado estado = null;
		
		if(value != null) {
			return estado = this.estadoDAO.buscarPorCodigo(Long.parseLong(value));
		}
		
		return estado;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Long codigo = ((Estado) value).getCodigo();//Faz o cast de objeto para long.
			return codigo == null ? null : codigo.toString();//Retorna o codigo
		}
		return null;
	}

}
