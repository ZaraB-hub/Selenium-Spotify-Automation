package SearchScenario;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.ThreadSafe;
import javax.swing.JScrollBar;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.commons.annotation.Testable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.annotations.GwtCompatible;

@TestMethodOrder(OrderAnnotation.class)
class Search {



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
	@Order(1)
	void searchTest() throws InterruptedException {
		webDriver.get(baseUrl);
		Thread.sleep(3000);
		
		//search button
		webDriver.findElement(By.cssSelector("#main > div > div.Root__top-container > nav > div.tUwyjggD2n5KvEtP5z1B > ul > li:nth-child(2) > a > span")).click();;
		Thread.sleep(3000);
		
		//enter smth into search bar
		String searchString="bts";
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/header/div[3]/div/div/form/input")).sendKeys(searchString);
		Thread.sleep(3000);
		
		//assert that the correct stuff is displayed based on our search
		String result=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div[2]/div/div/section[1]/div[2]/div/div/div/div[2]/a/div")).getText();
		assertEquals(searchString, result.toLowerCase());
		assertTrue(webDriver.getCurrentUrl().contains(searchString));
		
		//Verify that the user can click on a search result and be directed to the correct artist, album, or song page.
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div[2]/div/div/section[1]/div[2]/div/div/div/div[4]")).click();
		Thread.sleep(3000);
		String resultHeaderString=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/section/div/div[1]/div[2]/span[2]/h1")).getText();
		assertEquals(searchString, resultHeaderString.toLowerCase());
		
		Thread.sleep(3000);
	}
	
	@Test
	@Order(2)
	void categorizeResults() throws InterruptedException {
		//search button
		webDriver.findElement(By.cssSelector("#main > div > div.Root__top-container > nav > div.tUwyjggD2n5KvEtP5z1B > ul > li:nth-child(2) > a > span")).click();;
		Thread.sleep(3000);
		
		//enter smth into search bar
		String searchString="bts";
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/header/div[3]/div/div/form/input")).sendKeys(searchString);
		Thread.sleep(3000);

		//filter result by some category
		List<WebElement>searchOptions=webDriver.findElements(By.className("ZWI7JsjzJaR_G8Hy4W6J"));
		for(WebElement element:searchOptions) {
			element.click();
			Thread.sleep(2000);
		}
	}
	
	@Test
	@Order(3)
	void clearRecentSearches() throws InterruptedException {
		
		Thread.sleep(3000);
		//search button
		webDriver.findElement(By.cssSelector("#main > div > div.Root__top-container > nav > div.tUwyjggD2n5KvEtP5z1B > ul > li:nth-child(2) > a > span")).click();;
		Thread.sleep(3000);
	 
		//get x button
	    List<WebElement> removeElements = webDriver.findElements(By.className("xmJl0s8mcJ3bfhtnoaP1"));

	    for(WebElement element : removeElements){
	        element.click();
	        Thread.sleep(2000);
	    }
	    //assert there are no more recent searches
	    removeElements = webDriver.findElements(By.className("xmJl0s8mcJ3bfhtnoaP1"));
	    assertEquals(0, removeElements.size());
	    Thread.sleep(2000);
    
	}
	
	@Test
	void testBrowsign() throws InterruptedException {
		webDriver.get(baseUrl);
		Thread.sleep(3000);
		//search button
		webDriver.findElement(By.cssSelector("#main > div > div.Root__top-container > nav > div.tUwyjggD2n5KvEtP5z1B > ul > li:nth-child(2) > a > span")).click();;
		Thread.sleep(3000);
		
		JavascriptExecutor jsExecutor=(JavascriptExecutor) webDriver;
		List<WebElement>browseCategories=webDriver.findElements(By.className("Em2LrSSfvrgXQoajs6cm"));
		List<String> expected=new ArrayList<String>();  
		List<String> result=new ArrayList<String>(); 
		
		for(int i=0;i<browseCategories.size();i++) {
			if(i==1 || i==2)
				continue;
			expected.add(browseCategories.get(i).getText());
		}
		
		for(int i=1;i<=browseCategories.size();i++) {
			WebElement div=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/div/div/div/section/div[2]/a["+i+"]/div"));
			jsExecutor.executeScript("arguments[0].click()",div);
			Thread.sleep(3000);
			try {
				WebElement genre=webDriver.findElement(By.className("rEN7ncpaUeSGL9z0NGQR"));
				Thread.sleep(2000);
				assertTrue(genre.isDisplayed() && genre.isEnabled());
				Thread.sleep(2000);
				String genreString=genre.getText();
				result.add(genreString);
			} catch (NoSuchElementException e) {
				try {
		            String genreString = webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[2]/div/section/div[1]/div/div/h2")).getText();
		           // result.add(genreString);
				} catch (NoSuchElementException e2) {
		            //TODO
		        }
		    }
			
			webDriver.navigate().back();
			Thread.sleep(2000);
	

	}
	
	System.out.println(expected);
	System.out.println(result);
	
	assertEquals(expected, result);


	}
	
}




	
			

	


