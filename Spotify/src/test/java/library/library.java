package library;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

class library {


	private static WebDriver webDriver;
	private static String baseUrl;
	private static Actions actions;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\zarab\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options=new ChromeOptions();
		options.addArguments(" --start-maximized");
	//	options.addArguments("--headless");
		options.addArguments("--user-data-dir=\\Users\\zarab\\AppData\\Local\\Google\\Chrome\\User Data");
		webDriver=new ChromeDriver(options);
		baseUrl="https://www.spotify.com";
		actions=new Actions(webDriver);
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}
	
	@Test
	void accessAndBrowserLibrary() throws InterruptedException{
		webDriver.get(baseUrl);
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//library
		webDriver.findElement(By.partialLinkText("Library")).click();
		
		//browse by category
		List<WebElement> list=webDriver.findElements(By.className("OEFWODerafYHGp09iLlA"));
		
		for(WebElement element : list){
	        element.click();
			webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			assertTrue(webDriver.getCurrentUrl().contains(element.getText().toLowerCase()));
			Thread.sleep(2000);
	    }
	}
	
	

}
