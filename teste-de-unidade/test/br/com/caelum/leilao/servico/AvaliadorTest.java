package br.com.caelum.leilao.servico;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// parte 1: cenario
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		// parte 2: acao
		Avaliador leiloeiro = new Avaliador();
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
		Usuario pedro = new Usuario("Pedro");
		Usuario aline = new Usuario("Aline");
		Usuario cesar = new Usuario("Cesar");

		Leilao leilao = new Leilao("Pintura Leonardo da Vinci");

		Lance lanceCesar = new Lance(cesar, 300.0);
		leilao.propoe(lanceCesar);
		Lance lanceAline = new Lance(aline, 800.0);
		leilao.propoe(lanceAline);
		Lance lancePedro = new Lance(pedro, 699.0);
		leilao.propoe(lancePedro);

		// parte 2: acao
		Avaliador leiloeiro = new Avaliador();
		double valorMedioCalculado = leiloeiro.getValorMedioLance(leilao);

		// parte 3: validacao
		double valorMedioEsperado = (lanceCesar.getValor() + lanceAline.getValor() + lancePedro.getValor()) / 3;
		assertEquals(valorMedioEsperado, valorMedioCalculado, 0.00001);
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Usuario joao = new Usuario("João");
		Leilao leilao = new Leilao("Tapete");
		leilao.propoe(new Lance(joao, 1000.0));
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Tablet");
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());
		assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
	}

	@Test
	public void deveEntenderLeilaoComValorLanceAleatorio() {
		Usuario joao = new Usuario("João");
		Leilao leilao = new Leilao("Tapete");
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(joao, 450.0));
		leilao.propoe(new Lance(joao, 120.0));
		leilao.propoe(new Lance(joao, 700.0));
		leilao.propoe(new Lance(joao, 630.0));
		leilao.propoe(new Lance(joao, 230.0));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		assertEquals(700.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(120.0, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEntenderLeilaoComValorLanceOrdemDecrescente() {
		Usuario joao = new Usuario("João");
		Leilao leilao = new Leilao("Tapete");
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(joao, 100.0));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(100.0, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void umleilaoComCincoLancesDeveEncontrarOsTresMaiores() {
		Usuario abner = new Usuario("Abner");
		Usuario carlos = new Usuario("Carlos");
		Usuario regina = new Usuario("Regina");
		Usuario aline = new Usuario("Aline");
		Usuario marcia = new Usuario("Marcia");

		Leilao leilao = new Leilao("Notebook");

		leilao.propoe(new Lance(abner, 300.0));
		leilao.propoe(new Lance(carlos, 250.0));
		leilao.propoe(new Lance(regina, 269.0));
		leilao.propoe(new Lance(aline, 500.0));
		leilao.propoe(new Lance(marcia, 390.0));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());

		assertEquals(500.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(390.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(2).getValor(), 0.00001);
	}

	@Test
	public void umleilaoComDoislancesDeveDevolverApenasDoisLances() {
		Usuario abner = new Usuario("Abner");
		Usuario carlos = new Usuario("Carlos");

		Leilao leilao = new Leilao("Notebook");

		leilao.propoe(new Lance(abner, 300.0));
		leilao.propoe(new Lance(carlos, 250.0));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(2, maiores.size());

		assertEquals(300.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(250.0, maiores.get(1).getValor(), 0.00001);
	}
	
	@Test
	public void umLeilaoSemNenhumLanceDevolveListaVazia() {

		Leilao leilao = new Leilao("Notebook");

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(0, maiores.size());
	}
}
