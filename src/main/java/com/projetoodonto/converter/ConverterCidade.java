package com.projetoodonto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.projetoodonto.dao.CidadeDAO;
import com.projetoodonto.model.Cidade;
import com.projetoodonto.model.Estado;

@FacesConverter(value="cidadeConverter")
public class ConverterCidade implements Converter {

	@Inject
	private CidadeDAO cidadeDAO;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cidade cidade = null;
		
		if(value != null) {
			return cidade = this.cidadeDAO.buscarPorCodigo(Long.parseLong(value));
		}
		
		return cidade;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Long codigo = ((Cidade) value).getCodigo();//Faz o cast de objeto para long.
			return codigo == null ? null : codigo.toString();//Retorna o codigo
		}
		return null;
	}

}
