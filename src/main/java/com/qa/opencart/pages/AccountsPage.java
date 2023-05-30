package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. constructor of the page class
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	// private by locators
	private By accHeaders = By.cssSelector("div#content h2");
	private By myAccount = By.linkText("My Account");
	private By search = By.xpath("//input[@name='search']");
	private By searchIcon = By.cssSelector("div#search button");
	private By logOut = By.linkText("Logout");

	// 3. public page actions/methods
	public String getAccountPageTitle() {
		return eleUtil.waitForTitleIsAndCapture(AppConstants.ACCOUNTS_PAGE_TITLE_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
	}

	public boolean isLogOutLinkExist() {
		return eleUtil.IsElementDisplayed(logOut);
	}

	public boolean isMyAccountLinkExist() {
		return eleUtil.IsElementDisplayed(myAccount);
	}

	public List<String> getAccountPageHeaderList() {
		List<WebElement> accHeaderLinkList = eleUtil.waitForElementsVisible(accHeaders, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> headersValueList = new ArrayList<String>();
		for (WebElement e : accHeaderLinkList) {
			String text = e.getText();
			headersValueList.add(text);
		}
		return headersValueList;
	}

	public ResultsPage doSearch(String searchItem) {
		eleUtil.waitForElementVisible(search, AppConstants.SHORT_DEFAULT_WAIT);
		eleUtil.doSendKeys(search, searchItem);
		eleUtil.doClick(searchIcon);
		// this method has to return next landing page object its called page chaining model
		return new ResultsPage(driver);
	}

}
