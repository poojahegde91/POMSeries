package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By resultsProduct = By.cssSelector("div.product-layout.product-grid");
	
	
	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public String getResultsPageTitile(String searchkey) {
		return eleUtil.waitForTitleIsAndCapture(searchkey, AppConstants.SHORT_DEFAULT_WAIT);
	}

	public int getProductResultscount() {
		int resultCount = eleUtil.waitForElementsVisible(resultsProduct, AppConstants.SHORT_DEFAULT_WAIT).size();
		System.out.println("Product search results count ===> " + resultCount);
		return resultCount;
	}
	
	public ProductInfoPage selectProduct(String productName)
	{
		By productNameLocator = By.linkText(productName);
		eleUtil.doClick(productNameLocator);
		return new ProductInfoPage(driver);
	}

}
