package SingUpScenario;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

class socials {

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
	//	webDriver.quit();
	}
	
	@Test
	void singUpWithInvalidInput() throws InterruptedException {
		
		webDriver.get(baseUrl);

		
		//click on the sing up for free button
		webDriver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/footer/div[1]/button/span")).click();
		//wait for page to load
		Thread.sleep(2000);
		
		//sing up with gooogle
		webDriver.findElement(By.partialLinkText("Google")).click();
		Thread.sleep(2000);
		
		//pick account
		webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div/div/ul/li[2]/div")).click();
		Thread.sleep(2000);
		
		String nameString=webDriver.findElement(By.name("displayname")).getAttribute("required value");
		
		//day
				webDriver.findElement(By.name("day")).sendKeys("2");
				
				//month
				Select monthSelect=new Select(webDriver.findElement(By.name("month")));
				monthSelect.selectByVisibleText("October");
				
				//year
				webDriver.findElement(By.name("year")).sendKeys("2000");
				
			
				//assert radioButtons - one is selected then others are not
				List<WebElement> radioButtons = webDriver.findElements(By.cssSelector("input[type='radio'][className='Radio-sc-tr5kfi-0']"));
				for (WebElement radioButton : radioButtons) {
				    radioButton.click();
				    for (WebElement otherRadioButton : radioButtons) {
				        if (!otherRadioButton.equals(radioButton)) {
				            assertFalse(otherRadioButton.isSelected());
				        }
				    }
				}
				
				//gender
				webDriver.findElement(By.xpath("/html/body/div[1]/main/div/div/form/fieldset/div/div[2]/label/span[1]")).click();
				Thread.sleep(2000);

				
				//captcha - manual
				Thread.sleep(12000);
				
				//submit
				webDriver.findElement(By.xpath("/html/body/div[1]/main/div/div/form/div[6]/div/button")).click();

				//after submit wait until page is loaded
				Thread.sleep(5000);
				//assert that we are singedIn with our account - that profile name we choose is displayed 
				String profileUsername=webDriver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/header/button[2]/span")).getText();
				assertEquals(nameString, profileUsername);
				
				File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
				try {
				    FileUtils.copyFile(screenshot, new File("screenshot.png"));
				} catch (IOException e) {
				    e.printStackTrace();
				}
	}

}