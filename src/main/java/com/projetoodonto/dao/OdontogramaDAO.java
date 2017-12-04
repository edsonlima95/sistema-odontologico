package com.projetoodonto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.projetoodonto.model.Cliente;
import com.projetoodonto.model.Dente;
import com.projetoodonto.model.Odontograma;

public class OdontogramaDAO implements Serializable{

	@Inject
	private EntityManager manager;
	
	public void salvar(Odontograma odontograma) {
		this.manager.merge(odontograma);
	}
	
	public List<Odontograma> buscarPorCliente(Cliente cliente){
		return this.manager.createQuery("select o from Odontograma o where o.cliente = :codigo",Odontograma.class)
				.setParameter("codigo",cliente)
				.getResultList();
	}
	
	public Odontograma buscarPorCodigo(Long codigo) {
		return this.manager.find(Odontograma.class, codigo);
	}
	
	public void deletarOdontograma(Long codigo) {
		Odontograma odontograma = this.buscarPorCodigo(codigo);
		this.manager.remove(odontograma);
	}
	
	public Odontograma buscarOdontoDentes(Long codigo){
		return this.manager.createQuery("select o from Odontograma o left join fetch o.dentes where o.codigo = :codigo",Odontograma.class)
				.setParameter("codigo",codigo)
				.getSingleResult();
	}
	
}
