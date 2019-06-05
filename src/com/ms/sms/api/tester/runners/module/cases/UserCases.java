package com.ms.sms.api.tester.runners.module.cases;

import java.util.Arrays;
import java.util.Iterator;

import com.ms.sms.api.tester.runners.module.UserTester;

public class UserCases 
{
	public enum Case
	{
		VALID("plivo1","20S0KPNOIM"),
		IN_VALID("plivo1","20S0KPNO");
		
		private String userName, authtoken;
		private Case(String userName,String authtoken)
		{
			this.userName = userName;
			this.authtoken = authtoken;
		}
		
		public String getUserName()
		{
			return this.userName;
		}
		
		public String getAuthtoken()
		{
			return this.authtoken;
		}
		
		static Iterator<Case> caseIterator()
		{
			return Arrays.asList(Case.values()).iterator();
		}
 	}
	
	public static void runUnit()
	{
		Iterator<Case> cases = Case.caseIterator();
		
		while(cases.hasNext())
		{
			Case useCase = cases.next();
			System.out.print("Case name --> "+ useCase.name());
			new UserTester(useCase).test();
		}
	}
}
