package com.amazon.util;

import java.util.logging.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebActionUtil {
public  WebDriver driver;
	
	public static JavascriptExecutor jsExecutor;
	public static Actions action;
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/*
	 * @Thulasi
	 * clickOnElement  method is used for clicking the element
	 */
public static void clickOnElement(WebElement element, String elementName)
{	
	try {
		logger.info("clicking on element");
		element.click();
	}catch(Exception E)
	{
		logger.info("element is not able to click");
	}
}

/*
 * @Thulasi
 * clickOnElementUsingJs  method is used for clicking the element using Java script executor
 */
public static void clickOnElementUsingJs(WebElement element, String elementName)
{
	try {
		logger.info("clicking on element using JS");
		jsExecutor.executeScript("arguments[0].click();", element);
	}catch(Exception E)
	{
		logger.info("element is not able to click");
	}
}

/*
 * @Thulasi
 * typingTheText  method is used for typing the text in textbox
 */
public static void typingTheText(WebElement element,String value, String elementName)
{
	try {
		logger.info("typing the text in text box");
		element.sendKeys(value);
		logger.info("User is able to type " + value + " into " + elementName);
	}catch(Exception E)
	{
		logger.info(" User is not able to type " + value + " into " + elementName);
	}

}

/*
 * @Thulasi
 * scrollToElement method is used for scrolling to the particular element
 */
public static void scrollToElement(WebElement element, String elementName)
{
	try
	{	logger.info("-------------Scrolling till the Element------------");
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	catch (Exception e)
	{
		logger.info(" User is not able to scrolling  into " + elementName);
	}
}
/*
 * sleep method
 */
public static void sleep(int value)
{
	try {
		Thread.sleep(value);
	} catch (InterruptedException e) {
		
		e.printStackTrace();
	}
}
}
