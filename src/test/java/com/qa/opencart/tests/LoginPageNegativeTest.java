package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageNegativeTest extends BaseTest {
	@DataProvider
	public Object[][] incorrectLoginTestData() {
		return new Object[][] { 
			{ "auto123@gmail.com", "123456" }, 
			{ "test@gmail.com", "1232548" }, 
			{ "auto", "test" },
			{ "@gmail.com", "" },
			{ " ", " " }

		};
	}

	@Test(dataProvider = "incorrectLoginTestData")
	public void loginWithWrongCredentialsTest(String userName, String password) {
		System.out.println("Wrong credentials are " + userName + " and " + password);
		Assert.assertTrue(loginPage.doLoginWithWrongCredentials(userName, password));
	}
}
