package com.selenium.crm.testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.crm.baseclass.TestBase;
import com.selenium.crm.constants.Common_Constants;
import com.selenium.crm.pages.HomePage;
import com.selenium.crm.pages.LoginPage;

public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest()
	{
		super();
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setUp()
	{
		initialization("CHROME");
		log.info("Application Launched Successfully");
		
		loginPage = new LoginPage();
	}
	
	@Test(priority=1, enabled=true)
	public void loginPageTitleTest()
	{
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, Common_Constants.LOGIN_PAGE_TITLE, "Login Page Title is not Matched");
		log.info("Login Page Title Verified");
	}
	
	@Test(priority=2, enabled=true)
	public void crmLogoImageTest()
	{
		boolean flag = loginPage.validateCRMImage();
		Assert.assertTrue(flag);
		log.info("CRM Logo Verified");
	}
	
	@Test(priority=3, enabled=true, invocationCount=1) 
	public void loginTest()
	{
		homePage = loginPage.login(property.getProperty("username"),property.getProperty("password"));
		log.info("Successfully Logged into CRM Application");
	}

}
