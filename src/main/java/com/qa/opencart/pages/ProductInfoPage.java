package com.qa.opencart.pages;

import java.io.ObjectOutputStream.PutField;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");

	private Map<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public String getProductHeaderName() {
		return eleUtil.doGetElementText(productHeader);
	}

	public int getProductImagesCount() {
		return eleUtil.waitForElementsVisible(productImages, AppConstants.SHORT_DEFAULT_WAIT).size();
	}

	public Map<String, String> getProductInfo() {
		//productInfoMap = new HashMap<String, String>(); //it doesnot maintain any order
		//{Brand=Apple, Availability=In Stock, extaxprice=$2,000.00, Product Code=Product 18, productname=MacBook Pro, Reward Points=800, productprice=$2,000.00}

		//productInfoMap = new LinkedHashMap<String, String>(); //maintains the order
		//{Brand=Apple, Product Code=Product 18, Reward Points=800, Availability=In Stock, productprice=$2,000.00, extaxprice=$2,000.00, productname=MacBook Pro}

		productInfoMap = new TreeMap<String, String>(); //alphabetical order sorted
		//{Availability=In Stock, Brand=Apple, Product Code=Product 18, Reward Points=800, extaxprice=$2,000.00, productname=MacBook Pro, productprice=$2,000.00}
		
		getProductMetaData();
		getProductPriceData();
		productInfoMap.put("productname", getProductHeaderName());
		return productInfoMap;
	}

	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		for (WebElement e : metaList) {
			String metaText = e.getText();
			String metaInfo[] = metaText.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value);
		}
	}

	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		String priceValue = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText();
		String exTaxPricveValue = exTaxPrice.split(":")[1].trim();
		productInfoMap.put("productprice", priceValue);
		productInfoMap.put("extaxprice", exTaxPricveValue);
	}

}
