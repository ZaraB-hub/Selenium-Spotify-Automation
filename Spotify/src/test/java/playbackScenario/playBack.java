package playbackScenario;

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
import org.openqa.selenium.interactions.Actions;

class playBack {


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
	void playBackTest() throws InterruptedException, UnsupportedFlavorException, IOException {
		webDriver.get(baseUrl);	
		Thread.sleep(5000);
				
		//pick Playlist
		WebElement playlistElement=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/section/div/div/section[1]/div[2]/div/div/div[3]"));
		playlistElement.click();
		Thread.sleep(3000);
		
		//play
		WebElement playMusic= webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[2]/div[2]/div[4]/div/div/div/div/div/button"));
		playMusic.click();
		Thread.sleep(3000);
		String song1=webDriver.getTitle();
		assertTrue(playMusic.getAttribute("aria-label").toLowerCase().contains("pause"));
		
		//pause
		WebElement stopMusic= webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[2]/div[2]/div[4]/div/div/div/div/div/button"));
		stopMusic.click();
		Thread.sleep(2000);
		assertTrue(playMusic.getAttribute("aria-label").toLowerCase().contains("play"));
		
		//skip
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[2]/footer/div/div[2]/div/div[1]/div[2]/button[1]")).click();
		Thread.sleep(2000);
		String song2=webDriver.getTitle();
		assertNotEquals(song1, song2);
		
		//goBack
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[2]/footer/div/div[2]/div/div[1]/div[1]/button[2]")).click();
		Thread.sleep(2000);
		String song3=webDriver.getTitle();
		assertEquals(song1, song3);
		
		//shuffle
		WebElement shuffle=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[2]/footer/div/div[2]/div/div[1]/div[1]/button[1]"));
		shuffle.click();
		Thread.sleep(2000);
		assertTrue(shuffle.getAttribute("aria-checked").toLowerCase().contains("true"));
		shuffle.click();
		Thread.sleep(2000);
		Thread.sleep(2000);
		
		//repeat
		WebElement repeat = webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[2]/footer/div/div[2]/div/div[1]/div[2]/button[2]"));
		repeat.click();
		Thread.sleep(2000);
		assertTrue(repeat.getAttribute("aria-checked").toLowerCase().contains("true"));
		Thread.sleep(2000);
		
		
	}
}
