package com.selenium.crm.testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.crm.baseclass.TestBase;
import com.selenium.crm.pages.ContactsPage;
import com.selenium.crm.pages.DealsPage;
import com.selenium.crm.pages.HomePage;
import com.selenium.crm.pages.LoginPage;
import com.selenium.crm.utils.TestUtil;

public class ContactsPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	DealsPage dealsPage;
	
	String sheetName = "Contacts"; 
	
	public ContactsPageTest()
	{
		super();
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setUp()
	{
		initialization("chrome");
		testUtil = new TestUtil();
		log.info("Application Launched Successfully");
		
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		dealsPage = new DealsPage();
		homePage = loginPage.login(property.getProperty("username"),property.getProperty("password"));
	}
	
	@Test(priority=1, enabled=true)
	public void verifyContactsPageLabelTest()
	{
		testUtil.switchToFrame("mainpanel");
		contactsPage = homePage.clickOnContactsLink();
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "Contacts Label is Missing in the Page");
		log.info("Verified Contacts Page Label");
	}
	
	@Test(priority=2, enabled=true)
	public void selectSingleContactsTest()
	{
		testUtil.switchToFrame("mainpanel");
		contactsPage = homePage.clickOnContactsLink();
		contactsPage.selectContactByName("Ram Kumar");
		log.info("Verified Single Contacts");
	}
	
	@Test(priority=3, enabled=true)
	public void selectMultipleContactsTest()
	{
		testUtil.switchToFrame("mainpanel");
		contactsPage = homePage.clickOnContactsLink();
		contactsPage.selectContactByName("Ram Kumar");
		contactsPage.selectContactByName("Sanjay Kumar");
		log.info("Verified Multiple Contacts");
	}
	
	@DataProvider
	public Object[][] getCRMContactsTestData()
	{
		Object data [][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4, enabled=true, dataProvider="getCRMContactsTestData")
	public void validateCreateNewContactTest(String Title, String FirstName, String LastName, String Company)
	{
		testUtil.switchToFrame("mainpanel");
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact(Title, FirstName, LastName, Company);
		log.info("New Contacts Created Successfully");
	}
	
}
