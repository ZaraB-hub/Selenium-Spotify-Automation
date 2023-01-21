package SingUpScenario;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;

class SingUpTest {

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
	void singUpTestWithEmailAdress() throws InterruptedException {
		webDriver.get(baseUrl);
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// cookies:
		webDriver.findElement(By.xpath("/html/body/div[13]/div[3]/div/div[2]/button")).click();
		
		Thread.sleep(2000);
		//click on the sing up for free button
		webDriver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/footer/div[1]/button/span")).click();
		
		//wait for page to load
		Thread.sleep(2000);
		// asserts that when we click the button we are transfered to the right page
		assertEquals(webDriver.getCurrentUrl(), "https://www.spotify.com/ba/signup?forward_url=https%3A%2F%2Fopen.spotify.com%2F%3F"); 

		//get all form elements and check if they are intractable
		List<WebElement> formElements=webDriver.findElements(By.className("Input-sc-1gbx9xe-0"));
		for(WebElement element:formElements) {
			assertTrue(element.isDisplayed());
			assertTrue(element.isEnabled());
		}
		
		//Fill in form
		//email
		webDriver.findElement(By.name("email")).sendKeys("zaratester71732@gmail.com");
		
		//confirm email
		webDriver.findElement(By.name("confirm")).sendKeys("zaratester71732@gmail.com");
		
		//password
		webDriver.findElement(By.name("password")).sendKeys("uniburch7");
		
		//profile name
		String nameString="zara717";
		webDriver.findElement(By.name("displayname")).sendKeys(nameString);
		
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
		
		
		//last two buttons
		webDriver.findElement(By.xpath("/html/body/div[1]/main/div/div/form/div[6]/div/label/span[1]")).click();
		webDriver.findElement(By.xpath("/html/body/div[1]/main/div/div/form/div[7]/div/label/span[1]")).click();
		
		//captcha - manual
		Thread.sleep(12000);
		
		//submit
		webDriver.findElement(By.xpath("/html/body/div[1]/main/div/div/form/div[9]/div/button/span[1]")).click();

		//after submit wait until page is loaded
		Thread.sleep(2000);
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
