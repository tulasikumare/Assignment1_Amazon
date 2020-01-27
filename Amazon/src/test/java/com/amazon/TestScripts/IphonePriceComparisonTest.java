package com.amazon.TestScripts;

import org.testng.annotations.Test;

import com.amazon.lib.BaseTest;
import com.amazon.pages.IphonePriceComparison;


public class IphonePriceComparisonTest extends BaseTest{
	@Test()
	public void iphonePriceInAmazonAndFlipkartTest() 
	{	// creating object for Iphone price comparison POM class
		IphonePriceComparison price =new IphonePriceComparison(driver);
	
			price.iphonePriceInAmazonAndFlipkart();
		
	}
}
