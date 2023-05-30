package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. constructor of the page class
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	// private by locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By footerLinks = By.xpath("//footer//a");
	private By loginErrorMessg = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	
	private By registerLink = By.linkText("Register");

	// 3. public page actions/methods
	@Step("Getting login page title")
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIsAndCapture(AppConstants.LOGIN_PAGE_TITLE_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
	}
	@Step("Getting login page url")
	public String getLoginPageUrl() {
		return eleUtil.waitForURLContainsAndCapture(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE, AppConstants.SHORT_DEFAULT_WAIT);

	}

	@Step("checking forgot pwd link exist or not")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.IsElementDisplayed(forgotPwdLink);
	}

	@Step("Getting footer links")
	public List<String> getFooterLinkList() {
		List<WebElement> footerLinkList = eleUtil.waitForElementsVisible(footerLinks, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> footerTextList = new ArrayList<String>();
		for (WebElement e : footerLinkList) {
			String text = e.getText();
			footerTextList.add(text);
		}
		return footerTextList;
	}

	@Step("login with username {0} and password {1}")
	public AccountsPage doLogin(String userName, String pwd) {
		eleUtil.waitForElementVisible(emailId, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(emailId, userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		// this method has to return next landing page object its called page chaining model
		return new AccountsPage(driver);
	}
	
	@Step("login with wrong username {0} and password {1}")
	public boolean doLoginWithWrongCredentials(String userName, String pwd) {
		eleUtil.waitForElementVisible(emailId, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(emailId, userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		// this method has to return next landing page object its called page chaining model
		String errorMessageText =eleUtil.doGetElementText(loginErrorMessg);
		System.out.println("Login unsuccessfull ----- " +errorMessageText);
		if(errorMessageText.contains(AppConstants.LOGIN_ERROR_MESSAGE))
		{
			return true;
		}
		else
			return false;
	}
	
	public RegisterPage navigateToRegisterLink()
	{
		eleUtil.doClick(registerLink, AppConstants.SHORT_DEFAULT_WAIT);
		return new RegisterPage(driver);
	}
}
