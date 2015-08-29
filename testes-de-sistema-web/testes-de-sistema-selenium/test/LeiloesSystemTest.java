import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LeiloesSystemTest {

    private WebDriver driver;
    private LeiloesPage leiloes;

    @Before
    public void inicializa() {
        this.driver = new FirefoxDriver();
        leiloes = new LeiloesPage(driver);

        UsuariosPage usuarios = new UsuariosPage(driver);
        usuarios.visita();
        usuarios.novo().cadastra("Paulo Henrique", "paulo@henrique.com");
    }
    

    @After
    public void finaliza(){
    	driver.close();
    }
    
    @Test
    public void deveCadastrarUmLeilao() {

        leiloes.visita();
        NovoLeilaoPage novoLeilao = leiloes.novo();
        novoLeilao.preenche("Geladeira", 123, "Paulo Henrique", true);

        assertTrue(leiloes.existe("Geladeira", 123, "Paulo Henrique", true));

    }
    
    @Test
    public void deveValidarUmLeilaoSemNome() {

        leiloes.visita();
        NovoLeilaoPage novoLeilao = leiloes.novo();
        novoLeilao.preenche("", 0, "Paulo Henrique", true);

        assertTrue(novoLeilao.validacaoDeProdutoApareceu());
        assertTrue(novoLeilao.validacaoDeValorApareceu());

    }
}
