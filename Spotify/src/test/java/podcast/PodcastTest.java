package podcast;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.plaf.basic.BasicArrowButton;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

@TestMethodOrder(OrderAnnotation.class)
class PodcastTest {

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
	@Order(1)
	void testPodcastSubscription() throws InterruptedException {
		webDriver.get(baseUrl);
		Thread.sleep(3000);
		String podcastGenre="Documentary";
		//search button
		webDriver.findElement(By.cssSelector("#main > div > div.Root__top-container > nav > div.tUwyjggD2n5KvEtP5z1B > ul > li:nth-child(2) > a > span")).click();;
		Thread.sleep(3000);
		
		//select podcast
		webDriver.findElement(By.linkText("Podcasts")).click();
		Thread.sleep(3000);
		
		//select genre
		webDriver.findElement(By.linkText(podcastGenre)).click();;
		Thread.sleep(3000);
		
		//select first podcast
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div[2]/div[2]/div/section/div[2]/div[1]/div/div[3]")).click();
		Thread.sleep(3000);
		assertTrue(webDriver.getCurrentUrl().contains("show"));
		
		//click follow button
		WebElement folloWebElement=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/section/div[3]/div[4]/div/div/div/div/button[1]"));
		folloWebElement.click();
		Thread.sleep(5000);
		assertTrue(folloWebElement.getAttribute("class").contains("fEbcweEiUPPy2eaIaD3F"));
		
	}
	
	@Test
	@Order(2)
	void testPodcastUnSubscription() throws InterruptedException {
		//click following button
		WebElement folloWebElement=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/section/div[3]/div[4]/div/div/div/div/button[1]"));
		folloWebElement.click();
		Thread.sleep(5000);
		assertFalse(folloWebElement.getAttribute("class").contains("fEbcweEiUPPy2eaIaD3F"));
		
	}
	
	@Test
	@Order(3)
	void testShare() throws InterruptedException, UnsupportedFlavorException, IOException {

		Actions actions=new Actions(webDriver);
		//more 
		webDriver.findElement(By.className("T0anrkk_QA4IAQL29get")).click();
		Thread.sleep(4000);
		
		//share

		WebElement share=webDriver.findElement(By.xpath("/html/body/div[14]/div/ul/li[2]/button"));
		actions.moveToElement(share).perform();
		Thread.sleep(3000);
		//copy link
		Thread.sleep(2000);
		webDriver.findElement(By.xpath("/html/body/div[14]/div/ul/li[2]/div/ul/li[1]/button")).click();
		Thread.sleep(5000);
		//test link
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferable = clipboard.getContents(null);
		String clipboardLink = (String) transferable.getTransferData(DataFlavor.stringFlavor);
		assertTrue( clipboardLink.contains(webDriver.getCurrentUrl()));
		Thread.sleep(3000);		

	}
	
	@Test
	@Order(4)
	void episodeFunctionality() throws InterruptedException, UnsupportedFlavorException, IOException {

		//select episode
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/section/div[4]/div[2]/div[1]")).click();
		Thread.sleep(2000);
		String episodeName=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/section/div[1]/div[5]/span/h1/span")).getText();
		
		//play
		webDriver.findElement(By.className("IeLnf2wUHVKqxhzBcBoM")).click();
		Thread.sleep(3000);
		
		//pause
		webDriver.findElement(By.className("IeLnf2wUHVKqxhzBcBoM")).click();
		Thread.sleep(3000);
		
		//save to library
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/section/div[3]/div[4]/div/div/div/div[2]/button[1]")).click();
		Thread.sleep(2000);
	
		//veryfy that the proper episode has been added
		webDriver.navigate().to("https://open.spotify.com/collection/episodes");
		Thread.sleep(3000);
		String latestAdd=webDriver.findElement(By.partialLinkText(episodeName)).getText();
		assertEquals(latestAdd.toLowerCase(), episodeName.toLowerCase());
	}

	
	
	
	
	
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
