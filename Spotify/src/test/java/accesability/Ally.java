package accesability;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v85.accessibility.model.AXNode;
import org.openqa.selenium.json.Json;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;
import com.fasterxml.jackson.core.JsonParser;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dev.failsafe.internal.util.Assert;

class Ally {


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
		//webDriver.quit();
	}
	
	@Test
	void singUpWithInvalidInput() throws InterruptedException, UnsupportedFlavorException, IOException {
		webDriver.get(baseUrl);
			
	
		AxeBuilder builder = new AxeBuilder();
		 Results results = builder.analyze(webDriver);
		 List<Rule> violations = results.getViolations();
		 if (violations.size() == 0)
		    {
			 	assertTrue(true);
		    }
		    else
		    {
		    	 File reportFile = new File("axe-report");
		    	 AxeReporter.writeResultsToJsonFile(reportFile.getName(), results);
		    	 assertEquals(0,violations.size(), violations.size() + " violations found. See the report at: " + reportFile.getAbsolutePath());
		    }
		
		
		
		
		
		}



	


}