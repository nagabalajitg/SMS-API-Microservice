package com.ms.sms.api.tester;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestInvoker 
{
	public static void main(String arg[])
	{
		Result result = JUnitCore.runClasses(Jsuite.class);
		
		for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
			
	      System.out.println(result.wasSuccessful());
	}
}


