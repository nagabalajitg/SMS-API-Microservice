package com.ms.sms.api.tester;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ms.sms.api.tester.runners.ModuleTester;
import com.ms.sms.api.tester.runners.ServiceTester;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	   ModuleTester.class,
	   ServiceTester.class
	})

public class Jsuite {

}
