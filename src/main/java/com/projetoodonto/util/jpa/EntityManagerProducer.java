package com.projetoodonto.util.jpa;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer implements Serializable {

	private EntityManagerFactory factory;

	public EntityManagerProducer() {
		this.factory = Persistence.createEntityManagerFactory("ProjetoOdonto");
	}

	@Produces
	@RequestScoped
	public EntityManager create() {
		return this.factory.createEntityManager();
	}

	public void close(@Disposes EntityManager manager) {
		manager.close();
	}

}
