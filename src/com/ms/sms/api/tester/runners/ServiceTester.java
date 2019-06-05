package com.ms.sms.api.tester.runners;

import org.junit.Test;

import com.ms.sms.api.tester.runners.module.cases.InBoundCases;
import com.ms.sms.api.tester.runners.module.cases.OutBoundCases;

public class ServiceTester 
{
	@Test
	public void test()
	{
		InBoundCases.runService();
		OutBoundCases.runService();
	}
}
