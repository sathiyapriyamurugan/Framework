package com.selenium.crm.testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.crm.baseclass.TestBase;
import com.selenium.crm.constants.Common_Constants;
import com.selenium.crm.pages.ContactsPage;
import com.selenium.crm.pages.DealsPage;
import com.selenium.crm.pages.HomePage;
import com.selenium.crm.pages.LoginPage;
import com.selenium.crm.utils.TestUtil;

public class HomePageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	DealsPage dealsPage;
	
	public HomePageTest()
	{
		super();
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setUp()
	{
		initialization("chrome");
		log.info("Application Launched Successfully");
		
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		dealsPage = new DealsPage();
		homePage = loginPage.login(property.getProperty("username"),property.getProperty("password"));
	}
	
	@Test(priority=1, enabled=true)
	public void verifyHomePageTitleTest()
	{
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, Common_Constants.HOME_PAGE_TITLE, "Home Page Title is not Matched");
		log.info("Home Page Title Verified");
	}
	
	@Test(priority=2, enabled=true)
	public void verifyUserNameTest()
	{
		testUtil.switchToFrame("mainpanel");
		Assert.assertTrue(homePage.verifyCorrectUserName());
		log.info("UserName Verified");
	}
	
	@Test(priority=3, enabled=true)
	public void verifyContactsLinkTest()
	{
		testUtil.switchToFrame("mainpanel");
		contactsPage = homePage.clickOnContactsLink();
		log.info("Switched into Frame and Clicked on Contacts Link");
	}
	
	@Test(priority=4, enabled=true)
	public void verifyDealsPageLinkTest()
	{
		testUtil.switchToFrame("mainpanel");
		dealsPage = homePage.clickOnDealsLink();
		log.info("Switched into Frame and Clicked on Deals Link");
	}
}
