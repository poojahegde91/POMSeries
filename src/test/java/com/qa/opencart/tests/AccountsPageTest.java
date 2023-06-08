package com.qa.opencart.tests;

import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accountPageTitleTest() {
		String actTitle = accountsPage.getAccountPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}

	@Test
	public void logOutLinkExistTest() {
		Assert.assertTrue(accountsPage.isLogOutLinkExist());
	}

	@Test
	public void myAccLinkExistTest() {
		Assert.assertTrue(accountsPage.isMyAccountLinkExist());
	}

	@Test
	public void accPageHeadersCountTest() {
		List<String> actAccHeaderList = accountsPage.getAccountPageHeaderList();
		int headersCount = actAccHeaderList.size();
		Assert.assertEquals(headersCount,4);
	}

	@Test
	public void accPageHeadersTest() {
		List<String> actAccHeaderList = accountsPage.getAccountPageHeaderList();
		Assert.assertEquals(actAccHeaderList, AppConstants.EXP_ACCOUNTS_HEADERS_LIST);
	}

}
