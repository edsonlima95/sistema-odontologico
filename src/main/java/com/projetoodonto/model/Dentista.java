package com.projetoodonto.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue(value="dentista")
public class Dentista extends Pessoa {

	private String cro;
	
	public String getCro() {
		return cro;
	}

	public void setCro(String cro) {
		this.cro = cro;
	}	
	
}
