package LoginScenario;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class Logout {

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
	void logoutTest() throws InterruptedException {
		webDriver.get(baseUrl);
		Thread.sleep(3000);
		
		//click profile
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/header/button[2]")).click();
		Thread.sleep(3000);
		
		//click logout
		webDriver.findElement(By.xpath("/html/body/div[14]/div/div/ul/li[7]/button")).click();
		Thread.sleep(6000);
		
		//check for new login button
		String loginButtonElement = webDriver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/header/div[6]/button[2]/span")).getText();
		assertEquals("Log in", loginButtonElement);
		
		
	}

}
	
