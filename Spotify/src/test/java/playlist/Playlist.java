package playlist;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import javax.swing.JScrollBar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class Playlist {

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
	
		//go to library
		webDriver.findElement(By.partialLinkText("Library")).click();
		assertTrue(webDriver.getCurrentUrl().contains("playlist"));
		
		Thread.sleep(3000);
		//create playlist
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/section/section/a")).click();
		Thread.sleep(4000);
		assertTrue(webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[1]/div[5]/span/button/span/h1")).getText().contains("My Playlist"));
		
		//ADD SONGS 
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[2]/div[3]/section/div/div/input")).sendKeys("bts");
		Thread.sleep(3000);
		List<WebElement>addsElements=webDriver.findElements(By.cssSelector("[aria-label='Add to Playlist']"));
		for(int i=0;i<3;i++) {
			addsElements.get(i).click();
			Thread.sleep(3000);
		}
		
		//DELETE FIRST SONG
		WebElement firstElement=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[2]/div[3]/div[1]/div[2]/div[2]/div[1]/div"));
		
		Actions actions=new Actions(webDriver);
		actions.moveToElement(firstElement).click().keyDown(Keys.DELETE).perform();
		
		Thread.sleep(5000);
		//edit
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[1]/div[5]/span/button")).click();
		Thread.sleep(3000);
		webDriver.findElement(By.xpath("/html/body/div[17]/div/div/div/div[2]/div[3]/textarea")).sendKeys("Selenium");
		Thread.sleep(1000);
		// save
		webDriver.findElement(By.xpath("/html/body/div[17]/div/div/div/div[2]/div[4]/button")).click();
		Thread.sleep(3000);
		
		//SHARE
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[2]/div[2]/div[4]/div/div/div/div/button")).click();
		Thread.sleep(5000);
		WebElement share=webDriver.findElement(By.xpath("/html/body/div[15]/div/ul/li[6]/button"));
		actions.moveToElement(share).perform();
		Thread.sleep(3000);
		webDriver.findElement(By.xpath("/html/body/div[15]/div/ul/li[6]/div/ul/li[1]/button")).click();
		Thread.sleep(5000);
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferable = clipboard.getContents(null);
		String clipboardLink = (String) transferable.getTransferData(DataFlavor.stringFlavor);
		assertTrue( clipboardLink.contains(webDriver.getCurrentUrl()));
	
		
		Thread.sleep(4000);
		
		//DELETE PLAYLIST
		WebElement playlist=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/nav/div[1]/div[2]/div/div[4]/div/div[4]/div/div/ul/div/div[2]/div/li/a"));
		actions.moveToElement(playlist).click().keyDown(Keys.DELETE).perform();
		
		Thread.sleep(3000);
		assertTrue(webDriver.findElement(By.xpath("/html/body/div[17]/div/div/div/h2")).getText().toLowerCase().contains("delete"));
		webDriver.findElement(By.xpath("/html/body/div[17]/div/div/div/div/button[2]")).click();
		
		Thread.sleep(3000);
		assertTrue(webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/section/section/h1")).getText().contains("first playlist"));
}
}
