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
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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

import net.bytebuddy.implementation.bytecode.ByteCodeAppender.Size;


@TestMethodOrder(OrderAnnotation.class)
class Playlist {

	private static WebDriver webDriver;
	private static String baseUrl;
	private static Actions actions;
	private static String sizeSong;
	
	
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
		sizeSong="0";
		
		
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}
	
	@Test
	@Order(1)
	void createPlaylistTest() throws InterruptedException{
		webDriver.get(baseUrl);
		Thread.sleep(3000);
		
		//create playlist button
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/nav/div[1]/div[2]/div/div[1]/button")).click();
		Thread.sleep(3000);
		assertTrue(webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[1]/div[5]/span/button/span/h1")).getText().contains("My Playlist"));
		
	}

	@Test
	@Order(2)
	void addSongs() throws InterruptedException{
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[2]/div[3]/section/div/div/input")).sendKeys("bts");
		Thread.sleep(3000);
		List<WebElement>addsElements=webDriver.findElements(By.cssSelector("[aria-label='Add to Playlist']"));
		for(int i=0;i<3;i++) {
			addsElements.get(i).click();
			Thread.sleep(3000);
		}
		sizeSong=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[2]/div[3]/div[1]/div[2]/div[2]")).getAttribute("childElementCount");
		assertNotEquals(0,sizeSong);
	}
	
	@Test
	@Order(3)
	void deleteSongs() throws InterruptedException{
		WebElement firstElement=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[2]/div[3]/div[1]/div[2]/div[2]/div[1]/div"));
		
		actions.moveToElement(firstElement).click().keyDown(Keys.DELETE).perform();
		Thread.sleep(3000);

		sizeSong=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[2]/div[3]/div[1]/div[2]/div[2]")).getAttribute("childElementCount");
		assertNotEquals(0,sizeSong);

		Thread.sleep(5000);
	
	}
	
	@Test
	@Order(4)
	void editPlaylistTest() throws InterruptedException{
		Thread.sleep(2000);
		//edit
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[1]/div[5]/span/button")).click();
		Thread.sleep(3000);
		webDriver.findElement(By.xpath("/html/body/div[17]/div/div/div/div[2]/div[3]/textarea")).sendKeys("Selenium");
		Thread.sleep(1000);
		// save change
		webDriver.findElement(By.xpath("/html/body/div[17]/div/div/div/div[2]/div[4]/button")).click();
		Thread.sleep(3000);
	}
		
	@Test
	@Order(5)
	void shareTest() throws InterruptedException, UnsupportedFlavorException, IOException{
		Thread.sleep(2000);
		//more
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[2]/div[2]/div[4]/div/div/div/div/button")).click();
		Thread.sleep(5000);
		//share
		WebElement share=webDriver.findElement(By.xpath("/html/body/div[15]/div/ul/li[7]/button"));
		actions.moveToElement(share).perform();
		Thread.sleep(3000);
		//copy link
		Thread.sleep(2000);
		webDriver.findElement(By.xpath("/html/body/div[15]/div/ul/li[7]/div/ul/li[1]/button")).click();
		Thread.sleep(5000);
		//test link
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferable = clipboard.getContents(null);
		String clipboardLink = (String) transferable.getTransferData(DataFlavor.stringFlavor);
		assertTrue( clipboardLink.contains(webDriver.getCurrentUrl()));
			
		Thread.sleep(4000);
		}	
	
	@Test
	@Order(6)
	void deletePlaylist() throws InterruptedException{
		
		//select and press delete
		WebElement playlist=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/nav/div[1]/div[2]/div/div[5]/div/div[4]/div/div/ul/div/div[2]/div[1]/li"));
		actions.moveToElement(playlist).click().keyDown(Keys.DELETE).perform();
		Thread.sleep(3000);
		
		//confirm
		assertTrue(webDriver.findElement(By.xpath("/html/body/div[17]/div/div/div/h2")).getText().toLowerCase().contains("delete"));
		webDriver.findElement(By.xpath("/html/body/div[17]/div/div/div/div/button[2]")).click();
		
		}
	
	
	
	
	
	
	
	
	
	

}

