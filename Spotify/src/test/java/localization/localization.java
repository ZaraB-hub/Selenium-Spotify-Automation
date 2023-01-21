package localization;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

class localization {



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
	void singUpWithInvalidInput() throws InterruptedException, UnsupportedFlavorException, IOException {
		webDriver.get(baseUrl);
		Thread.sleep(5000);
		
		//click profile
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/header/button[2]")).click();
		Thread.sleep(2000);
		//settings
		webDriver.findElement(By.linkText("Settings")).click();
		Thread.sleep(2000);
		
		//language
		String langugeString="de";
		Select language=new Select(webDriver.findElement(By.id("desktop.settings.selectLanguage")));
		language.selectByValue(langugeString);
		
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/div[2]/div/div[3]/button")).click();
		Thread.sleep(5000);

		String pageSource=webDriver.getPageSource();
		assertTrue(pageSource.contains("html lang=\""+langugeString+"\""));
		Thread.sleep(2000);
				
				//language
		language=new Select(webDriver.findElement(By.id("desktop.settings.selectLanguage")));
		language.selectByValue("en");
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/div[2]/div/div[3]/button")).click();




	}
		

}
