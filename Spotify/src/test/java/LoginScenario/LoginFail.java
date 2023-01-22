package LoginScenario;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class LoginFail {


	private static WebDriver webDriver;
	private static String baseUrl;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\zarab\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options=new ChromeOptions();
		options.addArguments(" --start-maximized");
	//	options.addArguments("--headless");
	//	options.addArguments("--user-data-dir=\\Users\\zarab\\AppData\\Local\\Google\\Chrome\\User Data");
		webDriver=new ChromeDriver(options);
		baseUrl="https://www.spotify.com";
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}
	
	@Test
	void loginWithInvalidInput() throws InterruptedException {

		webDriver.get(baseUrl);
		
		Thread.sleep(3000);
		// cookies:
		webDriver.findElement(By.xpath("/html/body/div[13]/div[3]/div/div[2]/button")).click();
		
		//login Button
		webDriver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/header/div[6]/button[2]/span")).click();
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//login page
		assertTrue(webDriver.findElement(By.id("login-to-continue")).getText().contains("log in"));
		
		//input form
		webDriver.findElement(By.id("login-username")).sendKeys("zaratester717@gmail.com");
		webDriver.findElement(By.id("login-password")).sendKeys("uniburch");
		
		//submit
		webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[1]/div[3]/div[2]/button/div[1]")).click();
		Thread.sleep(3000);
		
		//error message
		assertTrue(webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[1]/span")).getText().contains("Incorrect"));
		
		
	}
	
	

}
