package ProfileManagment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

@TestMethodOrder(OrderAnnotation.class)
class Profile {

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
	@Order(1)
	void viewEditProfile() throws InterruptedException{
		String userName="313gmiugyw5oanf2vyq53hj72kfu";
		String newDisplayNameValue="zake";
		//get spotify
		webDriver.get(baseUrl);
		Thread.sleep(3000);
		
		//click user
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/header/button[2]")).click();
		Thread.sleep(3000);		
		
		//click profile
		webDriver.findElement(By.linkText("Profile")).click();
		Thread.sleep(3000);
		assertTrue(webDriver.getCurrentUrl().contains("user/"+userName));
		
		//edit display name
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/section/div/div[1]/div[5]/span/button")).click();
		Thread.sleep(3000);
		
		//model opens
		WebElement editElement=webDriver.findElement(By.id("user-edit-name"));
		Thread.sleep(3000);
		editElement.clear();
		editElement.sendKeys(newDisplayNameValue);
		
		//save
		webDriver.findElement(By.xpath("/html/body/div[16]/div/div/div/form/div[3]/button")).click();
		Thread.sleep(3000);
		
		//asseret
		String newName=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/section/div/div[1]/div[5]/span/button/span/h1")).getText();
		assertEquals(newDisplayNameValue,newName);
		
	}
}

