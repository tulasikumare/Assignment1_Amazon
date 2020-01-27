package com.amazon.lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	public   WebDriver driver;
	public BasePage(WebDriver driver) {
		this.driver=driver;
		//Initializing the Page elements using page factory
		PageFactory.initElements(driver,this);
		
	}	
}
