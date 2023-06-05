package com.selenium.crm.utils;

import com.selenium.crm.constants.Timeout_constants;

public class StaticWaits {
	
	public static void staticShortWait()
	{
		try 
		{
			Thread.sleep(Timeout_constants.SHORT_WAIT);
		} 
		catch(InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void staticMediumWait()
	{
		try 
		{
			Thread.sleep(Timeout_constants.MEDIUM_WAIT);
		} 
		catch(InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void staticLongWait()
	{
		try 
		{
			Thread.sleep(Timeout_constants.LONG_WAIT);
		} 
		catch(InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

}
