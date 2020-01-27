package com.amazon.lib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public WebDriver driver;
	public static Properties prop;
	
	/*
	 * In Before suite launching the web browser and Application
	 */
	@BeforeSuite
	public void launchApplication()
	{
		try {
			// reading the property file
			prop=new Properties();
			FileInputStream fis=new FileInputStream(GenericLib.propertyfilePath1);
			try {
				prop.load(fis);
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
			// getting browser name from property file
			String browser=prop.getProperty("Browser");
		
			if (browser.equalsIgnoreCase("firefox"))
			{
				// Launching the fireFox browser
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addPreference("dom.webnotifications.enabled", false);
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver(firefoxOptions);			
			} 
			else if (browser.equalsIgnoreCase("safari")) 
			{
				//Launching the safari browser
				driver = new SafariDriver();
				logger.info("Running in the 'Safari' Browser - Local");
				
			} 
			else
			{
				// launching the chrome driver
				logger.info("Running in the 'Chrome' Browser - Local");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				WebDriverManager.chromedriver().arch64().arch32().setup();
				driver = new ChromeDriver(options);
				logger.info("Running in the 'Chrome' Browser - Local");
			
			}
			
			// Maximize the browser
			driver.manage().window().maximize();
			logger.info("Launching the Application");
			// getting Url from property file
			driver.get(prop.getProperty("amazonurl"));
		}
	/*
	 * In AfterSuite closing the Application
	 */
	@AfterSuite
	public void closingApplication()
	{
		if (driver != null) {
			driver.quit();
			logger.info("Closing Browser");
			
		} else {
			logger.info("@After suite driver instance is null");
			
		}
	}
}
