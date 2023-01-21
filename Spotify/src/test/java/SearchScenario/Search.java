package SearchScenario;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollBar;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
	void searchTestArtist() throws InterruptedException {
		webDriver.get(baseUrl);
		
		Thread.sleep(3000);
		//search button
		webDriver.findElement(By.cssSelector("#main > div > div.Root__top-container > nav > div.tUwyjggD2n5KvEtP5z1B > ul > li:nth-child(2) > a > span")).click();;
		Thread.sleep(3000);
//		//enter into search bar
//		String searchString="bts";
//		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/header/div[3]/div/div/form/input")).sendKeys(searchString);
//		Thread.sleep(3000);
//		//asserths that what we search for we got url and artists
//		String result=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div[2]/div/div/section[1]/div[2]/div/div/div/div[2]/a/div")).getText();
//		assertEquals(searchString, result.toLowerCase());
//		assertTrue(webDriver.getCurrentUrl().contains(searchString));
//		//Verify that the user can click on a search result and be directed to the correct artist, album, or song page.
//		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div[2]/div/div/section[1]/div[2]/div/div/div/div[4]")).click();
//		Thread.sleep(3000);
//		String resultHeaderString=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/section/div/div[1]/div[2]/span[2]/h1")).getText();
//		assertEquals(searchString, resultHeaderString.toLowerCase());
//		
//		//go back
//		webDriver.navigate().back();
//		Thread.sleep(3000);
//		
////		File scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
////		try {
////			FileUtils.copyFile(scrFile, new File("C:\\Users\\zarab\\Pictures\\tests\\screenshot.png"));
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//
//	//SEARCH BY CATEGORY
//	List<WebElement>searchOptions=webDriver.findElements(By.className("ZWI7JsjzJaR_G8Hy4W6J"));
//	for(WebElement element:searchOptions) {
//		element.click();
//		Thread.sleep(2000);
//	}
//	
//	//REMOVE RECENT SEARCH
//	webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/header/div[3]/div/div/form/input")).clear();
//    //find all elements with aria-label attribute equal to "remove"
//    List<WebElement> removeElements = webDriver.findElements(By.className("xmJl0s8mcJ3bfhtnoaP1"));
//    System.out.print(removeElements.size());
//    //click on all elements found
//    for(WebElement element : removeElements){
//        element.click();
//    }
//    //find all elements with aria-label attribute equal to "remove"
//    List<WebElement> remainingRemoveElements = webDriver.findElements(By.cssSelector("[aria-label='remove']"));
//    //assert that there are no more elements with that attribute
//    assertEquals(0, remainingRemoveElements.size());
//    Thread.sleep(3000);
//    
    
		
//BROWSE
	JavascriptExecutor jsExecutor=(JavascriptExecutor) webDriver;
	List<WebElement>browseCategories=webDriver.findElements(By.className("Em2LrSSfvrgXQoajs6cm"));
//	for(int j=0;j<browseCategories.size();j++)	{	
//		String expected=browseCategories.get(j).getText();
//		System.out.print(expected);
	List<String> expected=new ArrayList<String>();  
	List<String> result=new ArrayList<String>(); 
	
	for(int i=0;i<browseCategories.size();i++) {
		if(i==2 || i==3)
			continue;
		expected.add(browseCategories.get(i).getText());
	}
	
	for(int i=1;i<browseCategories.size();i++) {
		WebElement div=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/div/div/div/section/div[2]/a["+i+"]/div"));
		jsExecutor.executeScript("arguments[0].click()",div);
		Thread.sleep(3000);
		try {
			String genreString=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div[1]/div/div[4]/span/h1")).getText();	
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
	
	
	assertEquals(expected, result);


	}


}

	
			

	


