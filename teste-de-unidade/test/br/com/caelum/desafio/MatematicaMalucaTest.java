package br.com.caelum.desafio;

import org.junit.Test;
import static org.junit.Assert.*;

public class MatematicaMalucaTest {
	
	@Test
	public void contaMalucaValorMaiorTrinta(){
		MatematicaMaluca m = new MatematicaMaluca();
		
		int valorEsperado = 31*4;
		int retorno = m.contaMaluca(31);
		assertEquals(valorEsperado, retorno);
	}
	
	@Test
	public void contaMalucaValorMaiorDez(){
		MatematicaMaluca m = new MatematicaMaluca();
				
		int valorEsperado = 15*3;
		int retorno = m.contaMaluca(15);
		assertEquals(valorEsperado, retorno);			
	}
	
	@Test
	public void contaMalucaValorMenorDez(){
		MatematicaMaluca m = new MatematicaMaluca();

		int valorEsperado = 5*2;
		int retorno = m.contaMaluca(5);
		assertEquals(valorEsperado, retorno);
	}
	
	@Test
	public void contaMalucaValorIgualDez(){
		MatematicaMaluca m = new MatematicaMaluca();
				
		int valorEsperado = 10*2;
		int retorno = m.contaMaluca(10);
		assertEquals(valorEsperado, retorno);	
	}
	
	@Test
	public void contaMalucaValorIgualTrinta(){
		MatematicaMaluca m = new MatematicaMaluca();
				
		int valorEsperado = 30*3;
		int retorno = m.contaMaluca(30);
		assertEquals(valorEsperado, retorno);	
	}
}
