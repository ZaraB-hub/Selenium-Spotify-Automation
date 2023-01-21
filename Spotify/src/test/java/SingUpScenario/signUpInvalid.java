package SingUpScenario;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

class signUpInvalid {

	private static WebDriver webDriver;
	private static String baseUrl;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\zarab\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options=new ChromeOptions();
		options.addArguments(" --start-maximized");
	//	options.addArguments("--headless");
		options.addArguments("--user-data-dir=\\Users\\zarab\\AppData\\Local\\Google\\Chrome\\User Data");
		webDriver=new ChromeDriver(options);
		baseUrl="https://www.spotify.com";
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}
	
	@Test
	void singUpWithInvalidInput() throws InterruptedException {
		//radi ako stavim bey profila moram cookies

		webDriver.get("https://www.spotify.com/ba/signup?forward_url=https%3A%2F%2Fopen.spotify.com%2F%3F");
		
		//submit
		webDriver.findElement(By.xpath("/html/body/div[1]/main/div/div/form/div[9]/div/button/span[1]")).click();
		
		Thread.sleep(2000);
		//assert that if i submit with improper input i stay on same page and get error messages
		assertEquals(webDriver.getCurrentUrl(), "https://www.spotify.com/ba/signup?forward_url=https%3A%2F%2Fopen.spotify.com%2F%3F"); 
		
		//error messages are displayed
		List<WebElement> errors=webDriver.findElements(By.cssSelector("span.Text-sc-g5kv67-0.jpUeQF"));
		for(WebElement e:errors) {
			e.isDisplayed();
			System.out.print(e.getText()); //error messages
		}
			

	}

}
