package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;
import static br.com.caelum.leilao.matcher.LeilaoMatcher.temUmLance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;
import br.com.caelum.leilao.builder.CriadorDeLeilao;

public class LeilaoTest {
	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15").constroi();
		assertEquals(0, leilao.getLances().size());

		Lance lance = new Lance(new Usuario("Steve Jobs"), 2000);
		leilao.propoe(lance);

		assertThat(leilao.getLances().size(), equalTo(1));
		assertThat(leilao, temUmLance(lance));
	}

	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("Macbook PRO 15");
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		leilao.propoe(new Lance(new Usuario("Steve Wozniak"), 3000));

		assertEquals(2, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
	}

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Macbook PRO 15");
		Usuario steveJobs = new Usuario("Steve Jobs");
		leilao.propoe(new Lance(steveJobs, 2000.0));
		leilao.propoe(new Lance(steveJobs, 3000.0));
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.0001);
	}

	@Test
	public void naoDeveAceitarMaisQue5LancesDoMesmoUsuario() {
		Leilao leilao = new Leilao("Macbook PRO 15");
		Usuario steveJobs = new Usuario("Steve Jobs");
		Usuario billGates = new Usuario("Bill Gates");

		leilao.propoe(new Lance(steveJobs, 2000.0));
		leilao.propoe(new Lance(billGates, 3000.0));

		leilao.propoe(new Lance(steveJobs, 4000.0));
		leilao.propoe(new Lance(billGates, 5000.0));

		leilao.propoe(new Lance(steveJobs, 6000.0));
		leilao.propoe(new Lance(billGates, 7000.0));

		leilao.propoe(new Lance(steveJobs, 8000.0));
		leilao.propoe(new Lance(billGates, 9000.0));

		leilao.propoe(new Lance(steveJobs, 10000.0));
		leilao.propoe(new Lance(billGates, 11000.0));

		// deve ser ignorado
		leilao.propoe(new Lance(steveJobs, 12000.0));

		assertEquals(10, leilao.getLances().size());
		assertEquals(11000.0, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.00001);

	}

	@Test
	public void dobraLance() {
		Usuario cesar = new Usuario("Cesar");
		Usuario jose = new Usuario("Jose");
		Leilao leilao = new Leilao("Camaro Amarelo");
		leilao.propoe(new Lance(cesar, 50000.0));
		leilao.propoe(new Lance(jose, 55000.0));
		leilao.dobraLance(cesar);
		assertEquals(3, leilao.getLances().size());
		assertEquals(55000.0, leilao.getLances().get(1).getValor(), 0.00001);
		assertEquals(100000.0, leilao.getLances().get(2).getValor(), 0.00001);
	}

	@Test
	public void dobraLanceSemNenhumLanceAnterior() {
		Usuario cesar = new Usuario("Cesar");
		Usuario jose = new Usuario("Jose");
		Leilao leilao = new Leilao("Camaro Amarelo");
		leilao.propoe(new Lance(jose, 55000.0));
		leilao.dobraLance(cesar);
		assertEquals(1, leilao.getLances().size());
		assertEquals(55000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
}