package com.projetoodonto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.projetoodonto.dao.ProcedimentoDAO;
import com.projetoodonto.model.Procedimento;

//Não sei pq mas quando e para uma lista em um select tem que definir o valor.
@FacesConverter(value="procedimentoConverter")
public class ProcedimentoConverter implements Converter {

	@Inject
	private ProcedimentoDAO procedimentoDAO;
	
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Procedimento procedimento = null;
		
		if(value != null) {
			return procedimento = this.procedimentoDAO.buscarPorCodigo(Long.parseLong(value));
		}
		
		return procedimento;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Long codigo = ((Procedimento) value).getCodigo();//Faz o cast de objeto para long.
			return codigo == null ? null : codigo.toString();//Retorna o codigo
		}
		return null;
	}

}
