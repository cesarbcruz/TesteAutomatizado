package br.com.caelum.leilao.teste;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.modelo.Leilao;
import br.com.caelum.leilao.modelo.Usuario;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;

public class LeilaoWSTest {

	@Test
	public void deveRetornarLeilaoPeloId() {
	    
		JsonPath path = given()
	            .queryParam("leilao.id", 1)
	            .header("Accept", "application/json")
	            .get("/leiloes/show")
	            .andReturn().jsonPath();
	
	    Leilao leilao = path.getObject("leilao", Leilao.class);
	    
	    Usuario usuarioEsperado = new Usuario(1L, "Mauricio Aniche", "mauricio.aniche@caelum.com.br");
	    Leilao leilaoEsperado = new Leilao(1L, "Geladeira", 800.0, usuarioEsperado, false);
	
	    assertEquals(leilaoEsperado, leilao);
	
	}

	@Test
	public void deveRetornarNumeroDeLeiloes() {
		
		XmlPath path = given()
				.header("Accept", "application/xml")
				.get("/leiloes/total?_format=xml")
				.andReturn()
				.xmlPath();

		int total = path.getInt("int");
		
		assertEquals(2, total);
	
	}
	
}
