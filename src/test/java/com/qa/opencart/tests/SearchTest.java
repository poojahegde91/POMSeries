package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductdataProvider;

public class SearchTest extends BaseTest {

	@BeforeClass
	public void searchSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(dataProvider = "productDataWithSearchKey", dataProviderClass = ProductdataProvider.class)
	public void searchProductTest(String searchKey) {
		resultsPage = accountsPage.doSearch(searchKey);
		Assert.assertTrue(resultsPage.getProductResultscount() > 0);
	}

	@Test(dataProvider = "productDataWithSearchKey", dataProviderClass = ProductdataProvider.class)
	public void searchPageTitleTest(String searchKey) {
		resultsPage = accountsPage.doSearch(searchKey);
		String actSearchTitle = resultsPage.getResultsPageTitile(searchKey);
		System.out.println("Actual search title " + actSearchTitle);
		Assert.assertEquals(actSearchTitle, "Search - " + searchKey);
	}

	@Test(dataProvider = "productDataWithName", dataProviderClass = ProductdataProvider.class)
	public void selectProductTest(String searchKey, String productName) {
		resultsPage = accountsPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(productName);
		String actProductHeaderName = productInfoPage.getProductHeaderName();
		System.out.println("Actual product header name " + actProductHeaderName);
		Assert.assertEquals(actProductHeaderName, productName);
	}
 
	@Test(dataProvider = "productDataWithImages", dataProviderClass = ProductdataProvider.class)
	public void productImagesTest(String searchKey, String productName, int expImagesCount){
		resultsPage = accountsPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(productName);
		int actProductImagesCount = productInfoPage.getProductImagesCount();
		System.out.println("Actual product Images count " + actProductImagesCount);
		Assert.assertEquals(actProductImagesCount, expImagesCount);
	}
}
