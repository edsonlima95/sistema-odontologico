package com.projetoodonto.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public abstract class FacesUtil {

	public static void addMessage(Severity severidade, String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severidade, mensagem, mensagem));
	}

}
