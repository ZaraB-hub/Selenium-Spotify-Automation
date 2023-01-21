package ProfileManagmentScenario;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

class EditAccount {


	private static WebDriver webDriver;
	private static String baseUrl;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\zarab\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options=new ChromeOptions();
		options.addArguments(" --start-maximized");
	//	options.addArguments("--headless");
	//	options.addArguments("--user-data-dir=\\Users\\zarab\\AppData\\Local\\Google\\Chrome\\User Data");
		webDriver=new ChromeDriver(options);
		baseUrl="https://www.spotify.com";
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}
	
	
	@Test
	void viewAccount() throws InterruptedException {
		webDriver.get(baseUrl);
		Thread.sleep(3000);
		// cookies:
		webDriver.findElement(By.xpath("/html/body/div[13]/div[3]/div/div[2]/button")).click();
		
		//login Button
		webDriver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/header/div[6]/button[2]/span")).click();
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//login page
		assertTrue(webDriver.findElement(By.id("login-to-continue")).getText().contains("log in"));
		
		//input form
		webDriver.findElement(By.id("login-username")).sendKeys("zaratester717@gmail.com");
		webDriver.findElement(By.id("login-password")).sendKeys("uniburch7");
		
		//submit
		webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[1]/div[3]/div[2]/button/div[1]")).click();
		
		Thread.sleep(3000);
		//click on profile
		webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/header/button[2]")).click();
		Thread.sleep(2000);
		String handle1=webDriver.getWindowHandle();
		
		//go to new tab
		webDriver.findElement(By.xpath("/html/body/div[14]/div/div/ul/li[1]/button/span")).click();
		Thread.sleep(2000);

		for(String handle:webDriver.getWindowHandles()) {
			if(!handle.equals(handle1)) {
				webDriver.switchTo().window(handle);
				break;
			}
		}
		
		//viewing Account & valid account
		assertTrue(webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[3]/div[2]/div/div[1]/h1")).getText().contains("overview"));
		assertEquals("zaratester717@gmail.com", webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[3]/div[2]/div/article[1]/section/table/tbody/tr[2]/td[2]")).getText());
		
		//clickEdit
		webDriver.findElement(By.linkText("Edit profile")).click();
		Thread.sleep(2000);
		

		//change gender
		Select genderSelect=new Select(webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/div/article/section/form/section/div[3]/div[2]/select")));
		genderSelect.selectByValue("FEMALE");
		
		//save changes
		webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/div/article/section/form/div/button/span[1]")).click();
		
		

			

	}
}