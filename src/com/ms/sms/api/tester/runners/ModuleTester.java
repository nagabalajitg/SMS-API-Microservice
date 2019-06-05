package com.ms.sms.api.tester.runners;

import org.junit.Test;

import com.ms.sms.api.tester.runners.module.cases.InBoundCases;
import com.ms.sms.api.tester.runners.module.cases.OutBoundCases;
import com.ms.sms.api.tester.runners.module.cases.UserCases;

public class ModuleTester 
{
	@Test
	public void test()
	{
		UserCases.runUnit();
		InBoundCases.runUnit();
		OutBoundCases.runUnit();
	}
}
