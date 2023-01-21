package radio;

import static org.junit.jupiter.api.Assertions.*;

import org.bouncycastle.jcajce.provider.digest.Skein.HashMac_1024_1024;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v105.audits.model.SharedArrayBufferIssueDetails;

class radio {




	private static WebDriver webDriver;
	private static String baseUrl;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\zarab\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options=new ChromeOptions();
		options.addArguments(" --start-maximized");
		//options.addArguments("--headless");
		options.addArguments("--user-data-dir=\\Users\\zarab\\AppData\\Local\\Google\\Chrome\\User Data");
		webDriver=new ChromeDriver(options);
		baseUrl="https://www.spotify.com";
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}
	
	@Test
	void searchTestArtist() throws InterruptedException {
		webDriver.get(baseUrl);
		
		//click on first 
		Thread.sleep(3000);
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/section/div/div/section[1]/div[2]/div[1]/div/div[3]")).click();
		Thread.sleep(3000);	
		String urlString=webDriver.getCurrentUrl();
		//more
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[2]/div[2]/div[4]/div/div/div/div/button[2]")).click();
		Thread.sleep(3000);
		
		
		//radio
		webDriver.findElement(By.xpath("/html/body/div[15]/div/ul/li[2]/button")).click();
		Thread.sleep(3000);
		assertTrue(webDriver.getCurrentUrl().contains("playlist"));
		String h2=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/section/div[1]/div[5]/h2[1]")).getText();
		assertEquals(h2.toLowerCase(), "playlist radio");
		

		//NE RADI
//		//add to playlist
//		Thread.sleep(2000);
//		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[2]/div[2]/div[4]/div/div/div/div/button")).click();
//		//ad
//		webDriver.findElement(By.xpath("/html/body/div[14]/div/ul/li[2]/button")).click();
//		Thread.sleep(3000);
//		
//		//create
//		webDriver.findElement(By.xpath("/html/body/div[14]/div/ul/li[2]/div/ul/div/li[2]/button")).click();
	}

}
