import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UsuariosSystemTest {
	
	
	private FirefoxDriver driver;

    @Before
    public void inicializa() {
        driver = new FirefoxDriver();
    }
    
    @After
    public void finaliza() {
        driver.close();
    }

	
    @Test
    public void deveAdicionarUmUsuario() {
        
        driver.get("http://localhost:8080/usuarios/new");

        WebElement nome = driver.findElement(By.name("usuario.nome"));
        WebElement email = driver.findElement(By.name("usuario.email"));

        nome.sendKeys("Adriano Xavier");
        email.sendKeys("axavier@empresa.com.br");
        nome.submit();

        boolean achouNome = driver.getPageSource()
            .contains("Adriano Xavier");
        boolean achouEmail = driver.getPageSource()
            .contains("axavier@empresa.com.br");

        assertTrue(achouNome);
        assertTrue(achouEmail);
    }
    
    @Test
    public void deveValidarNomeVazio() {
        
        driver.get("http://localhost:8080/usuarios/new");

        WebElement email = driver.findElement(By.name("usuario.email"));

        email.sendKeys("cesar.analista@gmail.com");
        email.submit();

        assertTrue(driver.getPageSource().contains("Nome obrigatorio!"));
        
    }
    
    @Test
    public void deveValidarNomeEmailVazio() {
        
        driver.get("http://localhost:8080/usuarios/new");

        WebElement email = driver.findElement(By.name("usuario.email"));
        email.submit();

        assertTrue(driver.getPageSource().contains("Nome obrigatorio!"));
        assertTrue(driver.getPageSource().contains("E-mail obrigatorio!"));
        
    }
    
    @Test
    public void verificarLinkNovoUsuario() {
        
        driver.get("http://localhost:8080/usuarios");

        WebElement link = driver.findElement(By.linkText("Novo Usuário"));
        link.click();

        assertTrue(driver.getPageSource().contains("Nome:"));
        assertTrue(driver.getPageSource().contains("E-mail:"));
        assertTrue(driver.getPageSource().contains("Salvar!"));
        
    }
}