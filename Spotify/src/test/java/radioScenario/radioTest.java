package radioScenario;

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
import org.openqa.selenium.interactions.Actions;


class radioTest {

	private static WebDriver webDriver,driver;
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
	void testRadio() throws InterruptedException {
		webDriver.get(baseUrl);
		
		//click on playlist 
		Thread.sleep(3000);
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/section/div/div/section[1]/div[2]/div[1]/div/div[3]")).click();
		Thread.sleep(3000);	
		
		//more option
		webDriver.findElement(By.className("T0anrkk_QA4IAQL29get")).click();
		Thread.sleep(4000);
		
		//go to radio 
		webDriver.findElement(By.xpath("/html/body/div[15]/div/ul/li[2]/button")).click();
		Thread.sleep(3000);
		
		//assert 
		assertTrue(webDriver.getCurrentUrl().contains("playlist"));
		String h2=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/section/div[1]/div[5]/h2[1]")).getText();
		assertTrue(h2.toLowerCase().contains("playlist") || h2.toLowerCase().contains("radio"));
	}
	
	@Test
	void addToPlaylist() throws InterruptedException {
		//more
		webDriver.get("https://open.spotify.com/station/playlist/37i9dQZF1DXcBWIGoYBM5M");
		Thread.sleep(3000);
		webDriver.findElement(By.className("T0anrkk_QA4IAQL29get")).click();
		Thread.sleep(4000);
		
		//hover over add to playlist
		Actions actions=new Actions(webDriver);
		WebElement addElement=webDriver.findElement(By.xpath("/html/body/div[14]/div/ul/li[2]/button"));
		actions.moveToElement(addElement).perform();
		Thread.sleep(1000);
		
		//create new playlist
		WebElement createPlaylist=webDriver.findElement(By.xpath("/html/body/div[14]/div/ul/li[2]/div/ul/div/li[2]/button"));
		createPlaylist.click();
		Thread.sleep(2000);
		
	}
	
	@Test
	void testRadiofromArtist() throws InterruptedException {
			
		//artist name
		String nameString="john legend";
		
		webDriver.get(baseUrl);
		Thread.sleep(3000);
		
		//search button
		webDriver.findElement(By.cssSelector("#main > div > div.Root__top-container > nav > div.tUwyjggD2n5KvEtP5z1B > ul > li:nth-child(2) > a > span")).click();;
		Thread.sleep(3000);
		
		//enter artist into search bar
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/header/div[3]/div/div/form/input")).sendKeys(nameString);
		Thread.sleep(3000);
		
		//go to artist 
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div[2]/div/div/section[1]/div[2]/div/div/div/div[4]")).click();
		Thread.sleep(4000);

		
		//more option
		webDriver.findElement(By.className("T0anrkk_QA4IAQL29get")).click();
		Thread.sleep(4000);
		
		//go to radio 
		webDriver.findElement(By.xpath("/html/body/div[15]/div/ul/li[2]/button")).click();
		Thread.sleep(3000);
		
		//assert 
		assertTrue(webDriver.getCurrentUrl().contains("playlist"));
		String h1=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[1]/div[5]/span/h1")).getText();
		assertEquals(nameString+" radio", h1.toLowerCase());
	}
	
	
	@Test
	void testRadiofromSong() throws InterruptedException {
		
		//song name
		String nameString="blank space";
		
		webDriver.get(baseUrl);
		Thread.sleep(3000);
		
		//search button
		webDriver.findElement(By.cssSelector("#main > div > div.Root__top-container > nav > div.tUwyjggD2n5KvEtP5z1B > ul > li:nth-child(2) > a > span")).click();;
		Thread.sleep(3000);
		
		//enter song into search bar
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/header/div[3]/div/div/form/input")).sendKeys(nameString);
		Thread.sleep(3000);
		
		//go to song 
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div[2]/div/div/section[1]/div[2]/div/div/div/div[4]")).click();
		Thread.sleep(4000);
		
		//more option
		List<WebElement> more=webDriver.findElements(By.className("T0anrkk_QA4IAQL29get"));
		more.get(2).click();
		Thread.sleep(5000);
		
		//go to radio 
		webDriver.findElement(By.xpath("/html/body/div[14]/div/ul/li[2]/button")).click();
		Thread.sleep(3000);
		
		//assert 
		assertTrue(webDriver.getCurrentUrl().contains("playlist"));
		String h1=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[1]/div[5]/span/h1")).getText();
		assertEquals(nameString+" radio", h1.toLowerCase());
	}
	
	
	
	
	
	
	
	
	
	
}
