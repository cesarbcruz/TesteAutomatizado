package br.com.caelum.leilao.dominio;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LanceTest {
	@Test(expected = IllegalArgumentException.class)
	public void naoPermitirLanceComValorIgualZero() {
		new Lance(new Usuario("Joao"), 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoPermitirLanceComValorMenorZero() {
		new Lance(new Usuario("Joao"), -100.0);
	}
	
	@Test()
	public void permitirLanceComValorMaiorZero() {
		Lance l = new Lance(new Usuario("Joao"), 500.0);
		assertEquals(500.0, l.getValor(), 0.00001);
	}
}
