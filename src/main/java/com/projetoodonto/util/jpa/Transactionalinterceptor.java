package com.projetoodonto.util.jpa;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transactional
public class Transactionalinterceptor implements Serializable {

	private @Inject EntityManager manager;

	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction transaction = manager.getTransaction();
		boolean owner = false;

		try {
			if (!transaction.isActive()) {
				// truque para fazer rollback no que j� passou
				// (sen�o, um futuro commit, confirmaria at� mesmo opera��es sem transa��o)
				transaction.begin();
				transaction.rollback();

				// agora sim inicia a transa��o
				transaction.begin();

				owner = true;
			}

			return context.proceed();
		} catch (Exception e) {
			if (transaction != null && owner) {
				transaction.rollback();
			}

			throw e;
		} finally {
			if (transaction != null && transaction.isActive() && owner) {
				transaction.commit();
			}
		}
	}

}
