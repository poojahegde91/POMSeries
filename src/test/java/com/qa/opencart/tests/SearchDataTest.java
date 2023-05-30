package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductdataProvider;
import com.qa.opencart.pojo.Product;

public class SearchDataTest extends BaseTest{

	@BeforeClass
	public void searchSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}


	@Test(dataProvider = "productData", dataProviderClass = ProductdataProvider.class)
	public void searchProductTest(Product product) {
		resultsPage = accountsPage.doSearch(product.getSearchKey());
		Assert.assertTrue(resultsPage.getProductResultscount() > 0);
	}

	@Test(dataProvider = "productData", dataProviderClass = ProductdataProvider.class)
	public void searchPageTitleTest(Product product) {
		resultsPage = accountsPage.doSearch(product.getSearchKey());
		String actSearchTitle = resultsPage.getResultsPageTitile(product.getSearchKey());
		System.out.println("Actual search title " + actSearchTitle);
		Assert.assertEquals(actSearchTitle, "Search - " + product.getSearchKey());
	}

	@Test(dataProvider = "productData", dataProviderClass = ProductdataProvider.class)
	public void selectProductTest(Product product) {
		resultsPage = accountsPage.doSearch(product.getSearchKey());
		productInfoPage = resultsPage.selectProduct(product.getProductName());
		String actProductHeaderName = productInfoPage.getProductHeaderName();
		System.out.println("Actual product header name " + actProductHeaderName);
		Assert.assertEquals(actProductHeaderName, product.getProductName());
	}
 
	@Test(dataProvider = "productData", dataProviderClass = ProductdataProvider.class)
	public void productImagesTest(Product product){
		resultsPage = accountsPage.doSearch(product.getSearchKey());
		productInfoPage = resultsPage.selectProduct(product.getProductName());
		int actProductImagesCount = productInfoPage.getProductImagesCount();
		System.out.println("Actual product Images count " + actProductImagesCount);
		Assert.assertEquals(actProductImagesCount, product.getProductImages());
	}
	
}
