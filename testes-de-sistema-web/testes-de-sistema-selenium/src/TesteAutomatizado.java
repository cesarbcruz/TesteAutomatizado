
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAutomatizado {

    public static void main(String[] args) {   	
    	// se for utilizar o chrome
    	System.setProperty("webdriver.chrome.driver","./driver/chromedriver");

    	// abre o chrome
    	WebDriver driver = new ChromeDriver();

    	// abre firefox
        //WebDriver driver = new FirefoxDriver();
    	
        // acessa o site do bing
        driver.get("http://www.bing.com/");

        // digita no campo com nome "q" do google
        WebElement campoDeTexto = driver.findElement(By.name("q"));
        campoDeTexto.sendKeys("Caelum");

        // submete o form
        campoDeTexto.submit();

    }
}

