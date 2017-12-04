package com.testes;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.projetoodonto.model.Consulta;
import com.projetoodonto.model.Convenio;
import com.projetoodonto.model.Dente;

public class TesteCliente {
	private static EntityManagerFactory factory;
	private EntityManager manager;

	@BeforeClass
	public static void init() {
		factory = Persistence.createEntityManagerFactory("ProjetoOdonto");
	}

	@Before
	public void setUp() {
		this.manager = this.factory.createEntityManager();
	}

	@After
	public void tearDown() {
		this.manager.close();
	}

	/*@Test
	public void calcularPorcentagem() {
		BigDecimal soma = this.manager.createQuery("select sum(p.valorPagamento) from Pagamento p", BigDecimal.class)
				.getSingleResult();
		List<Pagamento> pagamento = this.manager
				.createQuery("select p from Pagamento p group by p.tipoPagamento order by p.tipoPagamento asc", Pagamento.class).getResultList();

		for (Pagamento pag : pagamento) {
			
			 * Pega o valor de cada pagamento individual no campo -> pag.getValorPagamento()
			 * depois divide cada um pela soma de todos eles e
			 * arredonda para 2 casas depois da virgula -> .divide(soma,2,RoundingMode.HALF_EVEN)
			 * depois multiplica por 100 para da o resultado -> .multiply(new BigDecimal("100")).intValue();
			 * e retorna como Integer para as duas casas depois da virgula sair.
			 

			Integer porcentagem = pag.getValorPagamento().divide(soma, 2, RoundingMode.HALF_EVEN)
					.multiply(new BigDecimal("100")).intValue();
			System.out.println(pag.getTipoPagamento() + " - " + porcentagem + "%");
		}

	}*/
	@Test
	public void buscarDentes() {
		List<Dente> dentes = this.manager.createQuery("select d from Dente d",Dente.class).getResultList();
		for(Dente d : dentes) {
			System.out.println(d.getNumeroDente());
		}
	}
	
	@Test
	public void teste() {
		Calendar dataInicial = Calendar.getInstance();
		Calendar dataFinal = Calendar.getInstance();
		
		dataInicial.set(2017,Calendar.NOVEMBER, 06);
		dataFinal.set(Calendar.YEAR,Calendar.MONTH+1);

		Date dataI = dataInicial.getTime();
		Date dataF = dataFinal.getTime();
		
		String jpql = "select SUM(c.valorPagamento) from Consulta c where c.dataConsulta between :dataI and :dataF";
		
		BigDecimal consultasValorTotal = this.manager.createQuery(jpql,BigDecimal.class)
				.setParameter("dataI",dataI)
				.setParameter("dataI",dataF)
				.getSingleResult();	
		
		System.out.println(consultasValorTotal);
		
	}
}
