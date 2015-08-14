package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.infra.dao.RepositorioDeLeiloes;

public class EncerradorDeLeilaoTest {
	@Test
	public void deveEncerrarLeiloesQueComecaramUmaSemanaAntes(){
		Calendar antiga = Calendar.getInstance();
		antiga.set(1999, 1, 20);
		Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma").naData(antiga).constroi();
		Leilao leilao2 = new CriadorDeLeilao().para("Geladeira").naData(antiga).constroi();
		List<Leilao> leiloesAntigos=Arrays.asList(leilao1, leilao2);
		
		RepositorioDeLeiloes daoFalso = mock(RepositorioDeLeiloes.class);
		
		when(daoFalso.correntes()).thenReturn(leiloesAntigos);
		
		EncerradorDeLeilao encerrador = new EncerradorDeLeilao(daoFalso);
		encerrador.encerra();
		
		assertEquals(2, encerrador.getTotalEncerrados());
		assertTrue(daoFalso.correntes().get(0).isEncerrado());
		assertTrue(daoFalso.correntes().get(1).isEncerrado());
	}
	
	@Test
	public void naodeveEncerrarLeiloesQueComecaramOntem(){
		Calendar ontem = Calendar.getInstance();
		ontem.setTime(new Date());
		ontem.add(Calendar.DAY_OF_MONTH, -1);
		Leilao leilao1 = new CriadorDeLeilao().para("Tablet").naData(ontem).constroi();
		Leilao leilao2 = new CriadorDeLeilao().para("Notebook").naData(ontem).constroi();
		List<Leilao> leiloesOntem=Arrays.asList(leilao1, leilao2);
		
		RepositorioDeLeiloes daoFalso = mock(RepositorioDeLeiloes.class);
		
		when(daoFalso.correntes()).thenReturn(leiloesOntem);
		
		EncerradorDeLeilao encerrador = new EncerradorDeLeilao(daoFalso);
		encerrador.encerra();
		
		assertEquals(0, encerrador.getTotalEncerrados());
		assertFalse(daoFalso.correntes().get(0).isEncerrado());
		assertFalse(daoFalso.correntes().get(1).isEncerrado());
	}
	
	@Test
    public void naoDeveEncerrarLeiloesCasoNaoHajaNenhum() {

		RepositorioDeLeiloes daoFalso = mock(RepositorioDeLeiloes.class);
		
        when(daoFalso.correntes()).thenReturn(new ArrayList<Leilao>());

        EncerradorDeLeilao encerrador = new EncerradorDeLeilao(daoFalso);
        encerrador.encerra();

        assertEquals(0, encerrador.getTotalEncerrados());
    }
	
	
}

