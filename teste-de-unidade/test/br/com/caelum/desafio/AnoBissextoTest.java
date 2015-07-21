package br.com.caelum.desafio;

import org.junit.Test;
import static org.junit.Assert.*;

public class AnoBissextoTest {
	@Test
	public void ehAnoBissexto(){
		AnoBissexto ano = new AnoBissexto();
		assertTrue(ano.ehBissexto(2012));
		assertTrue(ano.ehBissexto(2016));
		assertTrue(ano.ehBissexto(800));
	}
	
	@Test
	public void naoEAnoBissexto(){
		AnoBissexto ano = new AnoBissexto();
		assertFalse(ano.ehBissexto(2011));
		assertFalse(ano.ehBissexto(1985));
		assertFalse(ano.ehBissexto(200));
	}
}
