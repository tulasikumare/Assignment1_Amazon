package com.amazon.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.amazon.lib.BasePage;
import com.amazon.util.WebActionUtil;



public class IphonePriceComparison extends BasePage{

	public IphonePriceComparison(WebDriver driver) {
		super(driver);
	
	}
	// typing product name in search text box
		@FindBy(xpath = "//div[@class='nav-search-field ']/input[@id='twotabsearchtextbox']")
		private WebElement productSerchTextBox;

		// clicking on search button
		@FindBy(xpath = "//div[@class='nav-search-submit nav-sprite']/input[@class='nav-input']")
		private WebElement productSerchButton;
		// getting on list of releated products
		@FindBy(xpath = "//div[@id='search']//div[@class='s-result-list s-search-results sg-row']/div//a[@class='a-link-normal a-text-normal']/span")
		private List<WebElement> productDetail;
		// getting price of Iphone
		@FindBy(xpath = "//div[@id='price']//td[@class='a-span12']/span[@id='priceblock_ourprice']")
		private WebElement iphonePriceInAmazon;

		// typing product name in search text box flipkart
		@FindBy(xpath = "//div[@class='O8ZS_U']/input[@title='Search for products, brands and more']")
		private WebElement productSerchTextBoxFlip;
		// typing product name in search text box flipkart
		@FindBy(xpath = "//button[@type='submit']")
		private WebElement productSerchButtonFlip;
		// clicking on product in flipkart
		@FindBy(xpath = "(//form[@action='/search']//ul/li/div/a)[1]")
		private WebElement productDetailsFlipKart;
		// getting list of products
		@FindBy(xpath = "//div[@class='_1HmYoV _35HD7C']/div[@class='bhgxx2 col-12-12']//div[@class='col col-7-12']/div[@class='_3wU53n']")
		private List<WebElement> productDetailsFlipKart1;
		// getting product price in flipkart
		@FindBy(xpath = "//div[@class='_1vC4OE _3qQ9m1']")
		private WebElement productpriceFlipKart;

		// getting product price in flipkart
		@FindBy(xpath = "//div[@class='col col-7-12']/div[@class='_3wU53n']")
		private WebElement productpriceFlipKart123;

		/*
		 * @Thulasi comparing the iPhone XR (64GB) - Yellow price both Amazon and
		 * flipkart
		 */
		public void iphonePriceInAmazonAndFlipkart() {
			com.amazon.util.WebActionUtil.typingTheText(productSerchTextBox, "iPhone XR (64GB) - Yellow",
					"typing the product name in search box");
			WebActionUtil.clickOnElement(productSerchButton, "clickinh on the search button");
			for (int i = 0; i < productDetail.size(); i++) {

				if (productDetail.get(i).getText().equalsIgnoreCase("Apple iPhone XR (64GB) - Yellow")) {

					productDetail.get(i).click();
					break;
				} else {
					WebActionUtil.logger.info(" nothing");
				}

			}

			ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
			// navigating to next tab
			driver.switchTo().window(tabs2.get(1));

			String AmazonPrice = iphonePriceInAmazon.getText();

			String price1 = AmazonPrice.split("\\s")[1];
			int value = Integer.parseInt(price1.split("\\,")[0]);
			double value1 = Double.parseDouble(price1.split("\\,")[1]);
			double amazonFinalPrice = value * 1000 + value1;

			System.out.println("Final price in Amazon: " + amazonFinalPrice);

			driver.close();
			driver.switchTo().window(tabs2.get(0));

			// opening the Flipkart Application
			driver.navigate().to("https://www.flipkart.com");
			WebActionUtil.sleep(20);
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).perform();
			WebActionUtil.typingTheText(productSerchTextBoxFlip, "iPhone XR (64GB) - Yellow",
					"typing the product name in search box");
			WebActionUtil.clickOnElement(productSerchButtonFlip, "clickinh on the search button");

			driver.navigate().to(
					"https://www.flipkart.com/search?q=iphone+xr+64gb+-+yellow&as=on&as-show=on&otracker=AS_Query_HistoryAutoSuggest_0_23_na_na_na&otracker1=AS_Query_HistoryAutoSuggest_0_23_na_na_na&as-pos=0&as-type=HISTORY&suggestionId=iphone+xr+64gb+-+yellow&requestId=219eca83-15aa-4099-8131-22813f65193a&as-searchtext=iphone%20xr%20(64gb)%20yellow");
			WebActionUtil.sleep(20);
		
			for (int i = 0; i < productDetailsFlipKart1.size(); i++) {

				if (productDetailsFlipKart1.get(i).getText().equalsIgnoreCase("Apple iPhone XR (Yellow, 64 GB)")) {

					productDetailsFlipKart1.get(i).click();
					break;
				} else {
					WebActionUtil.logger.info(" nothing");
				}

			}

			ArrayList<String> tabs3 = new ArrayList<String>(driver.getWindowHandles());
			// navigating to next tab
			driver.switchTo().window(tabs3.get(1));
			// getting price from flipkart
			String flipkartprice = productpriceFlipKart.getText();
			flipkartprice = flipkartprice.replaceAll("[^a-zA-Z0-9]", "");

			int flipkartFinalPrice = Integer.parseInt(flipkartprice);

			System.out.println("Final price in flipkart: " + flipkartFinalPrice);

			if (amazonFinalPrice == flipkartFinalPrice) {
				System.out.println("Both the Application have same price");
				WebActionUtil.logger.info("Both the Application have same price");
			} else if (amazonFinalPrice > flipkartFinalPrice) {
				System.out.println("amazon have  the more  price compare to flipkart");
				WebActionUtil.logger.info("amazon have  the more  price compare to flipkart");
			} else {
				WebActionUtil.logger.info("flipkart have  the more  price compare to amazon");
				System.out.println("flipkart have  the more  price compare to amazon");
			}
			driver.close();
			driver.switchTo().window(tabs2.get(0));
		}	
}
