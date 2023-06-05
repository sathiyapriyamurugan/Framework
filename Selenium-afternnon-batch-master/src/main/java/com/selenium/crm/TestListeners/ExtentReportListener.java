package com.selenium.crm.TestListeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.selenium.crm.Reports.ExtentReportSetup;
import com.selenium.crm.utils.TestUtil;

public class ExtentReportListener extends ExtentReportSetup implements ITestListener{

	public void onFinish(ITestContext context) {
		extent.flush();		
	}

	public void onStart(ITestContext context) {
		extent = ExtentReportSetup.extentReportSetup();		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		
		
	}

	public void onTestFailure(ITestResult result) {
		extentTest.log(Status.FAIL, "Test Case Failed is ::: " +result.getMethod().getMethodName());
		extentTest.log(Status.FAIL, result.getThrowable());
		
		try 
		{
			extentTest.addScreenCaptureFromPath(TestUtil.getScreenshot(driver, result.getMethod().getMethodName()));
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.SKIP, "Test Case Skipped is ::: " +result.getMethod().getMethodName());
		
	}

	public void onTestStart(ITestResult result) {
		extentTest = extent.createTest(result.getMethod().getMethodName());		
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, "Test Case Passed is ::: " +result.getMethod().getMethodName());
		
	}

}
