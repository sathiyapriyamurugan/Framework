package com.selenium.crm.baseclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.selenium.crm.constants.Browser_constants;
import com.selenium.crm.constants.Timeout_constants;
import com.selenium.crm.utils.TestUtil;
import com.selenium.crm.utils.WebEventListener;

public class TestBase {
	
	public static Logger log;
	public static WebDriver driver; 
	public static Properties property;
	public static ChromeOptions chromeOptions;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase() {
		log = Logger.getLogger(getClass().getName());
		String current_directory = System.getProperty("user.dir");
		System.out.println(current_directory);		
		String propfile_path = "/src/main/resources/testing.properties";
		try {
			property = new Properties();
			FileInputStream inputstream = new FileInputStream(current_directory+propfile_path);
			property.load(inputstream);
			System.out.println(property.getProperty("baseurl"));
			log.info("Property file path>>>>>>" + property.getProperty("baseurl"));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@BeforeTest
	public void setLog4j()
	{
		TestUtil.setDateForLog4j();
	}
	
	public static void initialization(String browserValue) {
		String broswerName = property.getProperty("browser");
		String[] split_browser_value = broswerName.split(",");
		
		if(split_browser_value[0].equalsIgnoreCase(browserValue)) {
			chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("useAutomationExtension", false);
			chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			System.setProperty(Browser_constants.CHROME_DRIVER_CLASS_NAME, Browser_constants.CHROME_DRIVER_PATH);
			driver = new ChromeDriver(chromeOptions);
			System.out.println("I am chrome driver");
			log.info("Chrome driver initiated........");
		}else if(split_browser_value[1].equalsIgnoreCase(browserValue)) {
			System.setProperty(Browser_constants.EDGE_DRIVER_CLASS_NAME, Browser_constants.EDGE_DRIVER_PATH);
			driver = new EdgeDriver();
			System.out.println("I am edge driver");
		}else if(split_browser_value[2].equalsIgnoreCase(browserValue)) {
			System.setProperty(Browser_constants.FIREFOX_DRIVER_CLASS_NAME, Browser_constants.FIREFOX_DRIVER_PATH);
			driver = new FirefoxDriver();
			System.out.println("I am firefox driver");
		}else if(split_browser_value[3].equalsIgnoreCase(browserValue)){
			System.setProperty(Browser_constants.IE_DRIVER_CLASS_NAME, Browser_constants.IE_DRIVER_PATH);
			driver = new InternetExplorerDriver();
			System.out.println("I am IE driver");
		}else {
			//System.out.println("None of the drivers initiated....");
			log.info("None of the drivers initiated....");
			
		}
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		
		driver.manage().timeouts().pageLoadTimeout(Timeout_constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Timeout_constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(property.getProperty("baseurl"));		
		
	}
	
	@AfterTest
	public void endReport()
	{
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() throws IOException
	{
		driver.quit();
		log.info("Browser Terminated");
		log.info("-----------------------------------------------");
	}
	
	/*public static void main(String... args) {
		TestBase tb = new TestBase();
		initialization("chrome");
	}*/

}
