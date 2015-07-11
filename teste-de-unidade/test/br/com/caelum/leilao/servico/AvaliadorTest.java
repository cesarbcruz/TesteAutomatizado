package br.com.caelum.leilao.servico;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;


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
		
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		
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
		double valorMedioEsperado = (lanceCesar.getValor()+lanceAline.getValor()+lancePedro.getValor())/3;
		Assert.assertEquals(valorMedioEsperado, valorMedioCalculado, 0.00001);
	}
}

