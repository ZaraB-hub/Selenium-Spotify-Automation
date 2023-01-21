package LoginScenario;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class LoginViaGoogle {


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
		//webDriver.quit();
	}
	
	@Test
	void test() {
		webDriver.get(baseUrl);
//		
//		// cookies:
//		webDriver.findElement(By.xpath("/html/body/div[13]/div[3]/div/div[2]/button")).click();
		
		//login Button
		webDriver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/header/div[6]/button[2]/span")).click();
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//login page
		assertTrue(webDriver.findElement(By.id("login-to-continue")).getText().contains("log in"));
		
		//signInviaGoogle
		webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/ul/li[3]/button")).click();
		
//		//input email
//		webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[1]/div/div[1]/div/div[1]/input")).click();
		
		webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div/div/ul/li[4]/div/div[1]/div")).click();
	
			//	failed not with this method
	}

}
