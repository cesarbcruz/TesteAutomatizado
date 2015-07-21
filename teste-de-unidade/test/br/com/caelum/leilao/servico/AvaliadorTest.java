package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {
	
	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario maria;
	private Usuario jose;
	private Usuario pedro;
	private Usuario aline;
	private Usuario cesar;
	private Usuario abner;
	private Usuario carlos;
	private Usuario regina;
	private Usuario marcia;
	
	@BeforeClass
	public static void testandoBeforeClass() {
	  System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
	  System.out.println("after class");
	}
	
	@Before
	public void criaAvaliador(){
		leiloeiro = new Avaliador();
		joao = new Usuario("João");
		jose = new Usuario("José");
		maria = new Usuario("Maria");
		pedro = new Usuario("Pedro");
		aline = new Usuario("Aline");
		cesar = new Usuario("Cesar");
		abner = new Usuario("Abner");
		carlos = new Usuario("Carlos");
		regina = new Usuario("Regina");
		marcia = new Usuario("Marcia");
		System.out.println("inicializando teste!");
	}
	
	@After
	public void finaliza() {
	  System.out.println("fim");
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// parte 1: cenario
		System.out.println("deveEntenderLancesEmOrdemCrescente");
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
		.lance(joao, 250.0)
		.lance(jose, 300.0)
		.lance(maria, 400.0).constroi();
		
		// parte 2: acao
		leiloeiro.avalia(leilao);

		// parte 3: validacao
		double maiorEsperado = 400;
		double menorEsperado = 250;

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);

	}

	@Test
	public void calcularValorMediaDosLances() {
		// parte 1: cenario
		Leilao leilao = new CriadorDeLeilao().para("Pintura Leonardo da Vinci")
		.lance(cesar, 300.0)
		.lance(aline, 800.0)
		.lance(pedro, 699.0).constroi();
		// parte 2: acao
		double valorMedioCalculado = leiloeiro.getValorMedioLance(leilao);

		// parte 3: validacao
		double valorMedioEsperado = (leilao.getLances().get(0).getValor() + leilao.getLances().get(1).getValor() + leilao.getLances().get(2).getValor()) / 3;
		assertEquals(valorMedioEsperado, valorMedioCalculado, 0.00001);
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Leilao leilao = new Leilao("Tapete");
		leilao.propoe(new Lance(joao, 1000.0));

		leiloeiro.avalia(leilao);
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Leilao leilao = new CriadorDeLeilao().para("Tablet")
				.lance(joao, 100)
				.lance(maria, 200)
				.lance(joao, 300)
				.lance(maria, 400)
				.constroi();

		leiloeiro.avalia(leilao);
		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());
		assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
	}

	@Test
	public void deveEntenderLeilaoComValorLanceAleatorio() {
		Leilao leilao = new Leilao("Tapete");
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(pedro, 450.0));
		leilao.propoe(new Lance(joao, 120.0));
		leilao.propoe(new Lance(pedro, 700.0));
		leilao.propoe(new Lance(joao, 630.0));
		leilao.propoe(new Lance(pedro, 230.0));

		leiloeiro.avalia(leilao);
		assertEquals(700.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(120.0, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEntenderLeilaoComValorLanceOrdemDecrescente() {
		Leilao leilao = new Leilao("Tapete");
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(pedro, 300.0));
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(pedro, 100.0));


		leiloeiro.avalia(leilao);
		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(100.0, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void umleilaoComCincoLancesDeveEncontrarOsTresMaiores() {

		Leilao leilao = new Leilao("Notebook");

		leilao.propoe(new Lance(abner, 300.0));
		leilao.propoe(new Lance(carlos, 250.0));
		leilao.propoe(new Lance(regina, 269.0));
		leilao.propoe(new Lance(aline, 500.0));
		leilao.propoe(new Lance(marcia, 390.0));


		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());

		assertEquals(500.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(390.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(2).getValor(), 0.00001);
	}

	@Test
	public void umleilaoComDoislancesDeveDevolverApenasDoisLances() {
		Leilao leilao = new Leilao("Notebook");

		leilao.propoe(new Lance(abner, 300.0));
		leilao.propoe(new Lance(carlos, 250.0));


		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(2, maiores.size());

		assertEquals(300.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(250.0, maiores.get(1).getValor(), 0.00001);
	}
	
	@Test
	public void umLeilaoSemNenhumLanceDevolveListaVazia() {

		Leilao leilao = new Leilao("Notebook");


		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(0, maiores.size());
	}
}
