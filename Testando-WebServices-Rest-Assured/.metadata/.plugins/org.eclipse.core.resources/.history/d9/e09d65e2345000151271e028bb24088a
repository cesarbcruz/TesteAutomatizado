package br.com.caelum.leilao.teste;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.modelo.Usuario;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;

public class UsuarioWSTest {

	private Usuario mauricio;
	private Usuario guilherme;
	
	@Before
	public void setup() {
	
		mauricio = new Usuario(1L, "Mauricio Aniche",
				"mauricio.aniche@caelum.com.br");
		guilherme = new Usuario(2L, "Guilherme Silveira",
				"guilherme.silveira@caelum.com.br");
	
	}

	@Test
	public void deveRetornarListaDeUsuarios() {

		XmlPath path = given().header("Accept", "application/xml")
				.get("/usuarios?_format=xml").andReturn().xmlPath();

		List<Usuario> usuarios = path.getList("list.usuario", Usuario.class);
		
		assertEquals(mauricio, usuarios.get(0));
		assertEquals(guilherme, usuarios.get(1));

	}

    @Test
    public void deveRetornarUsuarioPeloId() {
        
    	JsonPath path = given()
                .queryParam("usuario.id", 1)
                .header("Accept", "application/json")
                .get("/usuarios/show")
                .andReturn().jsonPath();

        Usuario usuario = path.getObject("usuario", Usuario.class);
    	
        Usuario esperado = new Usuario(1L, "Mauricio Aniche", "mauricio.aniche@caelum.com.br");

        assertEquals(esperado, usuario);

    }
    
    @Test
    public void deveAdicionarUmUsuario() {
        Usuario joao = new Usuario("Joao da Silva", "joao@dasilva.com");

        XmlPath retorno = 
            given()
                .header("Accept", "application/xml")
                .contentType("application/xml")
                .body(joao)
            .expect()
                .statusCode(200)
            .when()
                .post("/usuarios")
            .andReturn()
                .xmlPath();

        Usuario resposta = retorno.getObject("usuario", Usuario.class);

        assertEquals("Joao da Silva", resposta.getNome());
        assertEquals("joao@dasilva.com", resposta.getEmail());

    }

}
