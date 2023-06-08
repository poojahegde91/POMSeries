package com.qa.opencart.tests;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void RegisterPageSetUp() {
		registerPage = loginPage.navigateToRegisterLink();
	}

	public String getRandomEmailID() {
		String email = null;
		email = "testautomation" + System.currentTimeMillis() + "@gmail.com";
		System.out.println(email);
		return email;
//		return "testautomation" + UUID.randomUUID() + "@gmail.com";
	}

//	@DataProvider(name = "UserRegisterData")
//	public Object[][] getUserRegData() {
//		return new Object[][] { 
//				{ "pooja", "hegde", "1254789630", "pooja@123", "no" },
//				{ "Parvathi", "Mesta", "1254739630", "parua@123", "no" },
//				{ "Pavana", "Deepthi", "1254784530", "pav@123", "no" } };
//	}
	
	@DataProvider(name = "UserRegisterExcelData")
	public Object[][] getRegExcelTestData() {
		Object regData [][] =ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	 

	@Test(dataProvider = "UserRegisterExcelData")
	public void userRegisterTest(String firstName, String lastName, String telephone, String password,
			String subscribe) {
		String actRegSuccessMessage = registerPage.registerUser(firstName, lastName, getRandomEmailID(), telephone,
				password, subscribe);
		Assert.assertEquals(actRegSuccessMessage, AppConstants.USER_REG_SUCCESS_MESSG);
		System.out.println(password);
	}

}
