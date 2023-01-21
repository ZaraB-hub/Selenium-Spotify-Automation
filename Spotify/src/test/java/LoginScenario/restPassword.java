package LoginScenario;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.opentelemetry.api.trace.StatusCode;

class restPassword {

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
	void resetPassword() throws InterruptedException {
		webDriver.get(baseUrl);
		Thread.sleep(3000);
		
		// cookies:
		webDriver.findElement(By.xpath("/html/body/div[13]/div[3]/div/div[2]/button")).click();
		
		//login Button
		webDriver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/header/div[6]/button[2]/span")).click();
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//login page
		assertTrue(webDriver.findElement(By.id("login-to-continue")).getText().contains("log in"));
		
		//forgot password
		webDriver.findElement(By.linkText("Forgot your password?")).click();
		
		//assert that we are at the password reset route
		assertEquals("Password Reset",webDriver.findElement(By.xpath("/html/body/div[1]/div/section/div/h1")).getText());
		
		//enter email
		webDriver.findElement(By.id("email_or_username")).sendKeys("zara717tester@gmail.com");
		
		//send
		webDriver.findElement(By.xpath("/html/body/div[1]/div/section/div/form/div[2]/button/div[1]")).click();
		
		Thread.sleep(3000);
		//successfully send
		String sentString=webDriver.findElement(By.xpath("/html/body/div[1]/div/section/div/p")).getText();
		//System.out.print(sentString);
		assertTrue(sentString.contains("We've sent you an email. Just follow the instructions to reset your password."));

	
	}

}
