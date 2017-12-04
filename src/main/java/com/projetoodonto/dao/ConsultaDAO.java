package com.projetoodonto.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.projetoodonto.model.Cliente;
import com.projetoodonto.model.Consulta;

public class ConsultaDAO implements Serializable{

	@Inject
	private EntityManager manager;
	
	public void salvar(Consulta consulta) {
		this.manager.merge(consulta);
	}
	
	public List<Consulta> buscarTodos(){
		return this.manager.createQuery("select c from Consulta c",Consulta.class).getResultList();
	}
		
	public Consulta buscarPorCodigo(Long codigo) {
		return this.manager.find(Consulta.class,codigo);
	}
	
	public void deletar(Long codigo) {
		Consulta consulta = this.buscarPorCodigo(codigo);
		this.manager.remove(consulta);
	}
	
	/*OBS: o metodo getSingleResult caso n�o retorne um resultado uma exe��o e lan�ada
	 * nesse caso para funcionar precisa adicinar um bloco try catch*/
	public Consulta verificaData(Date data, Long codigo) {
		try {
			Consulta cc = (Consulta) this.manager.createQuery("select c from Consulta c"
					+ " where c.dataConsulta = :data and c.dentista.codigo = :codigo")
					.setParameter("data", data)
					.setParameter("codigo", codigo)
					.getSingleResult();
			return cc;
		} catch (NoResultException e) {
			return null;
		}
		
	}

	public List<Consulta> paginacao(int first, int pageSize) {
		return this.manager.createQuery("select c from Consulta c", Consulta.class)
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
	}

	public Long quantidade() {
		return this.manager.createQuery("select COUNT(c) from Consulta c",Long.class).getSingleResult();
	}
	
	public BigDecimal somaValorMes() {
		
		Calendar data = Calendar.getInstance();
		
		String jpql = "select SUM(c.valorPagamento) from Consulta c where c.dataConsulta like :dataI";
		
		BigDecimal valorTotal = (BigDecimal) this.manager.createNativeQuery(jpql)
				.setParameter("dataI","%"+data.get(Calendar.YEAR)+"-"+(data.get(Calendar.MONTH)+1)+"%")
				.getSingleResult();	
		
		return valorTotal;
	}
	
}
