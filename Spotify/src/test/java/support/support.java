//package support;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//class support {
//
//	private static WebDriver webDriver;
//	private static String baseUrl;
//	
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//		System.setProperty("webdriver.chrome.driver","C:\\Users\\zarab\\Downloads\\chromedriver_win32\\chromedriver.exe");
//		ChromeOptions options=new ChromeOptions();
//		options.addArguments(" --start-maximized");
//		//options.addArguments("--headless");
//		options.addArguments("--user-data-dir=\\Users\\zarab\\AppData\\Local\\Google\\Chrome\\User Data");
//		webDriver=new ChromeDriver(options);
//		baseUrl="https://www.spotify.com";
//		
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//		webDriver.quit();
//	}
//	
//	@Test
//	void searchTestArtist() throws InterruptedException {
//		webDriver.get(baseUrl);
//		
//
//		
//
//}
