package com.ms.sms.api.tester.runners.module.cases;

import java.util.Arrays;
import java.util.Iterator;

import com.ms.sms.api.tester.runners.module.InBoundTester;

public class InBoundCases implements MessageBoundCases
{
	public enum Case implements AbstractCase
	{
		VALID(UserCases.Case.VALID, "4924195509198","4924195509196","Hi, there."),
		NUMBER_BLOCKER(UserCases.Case.VALID, "4924195509198","4924195509196","STOP"),
		NUMBER_NOT_IN_ACCOUNT(UserCases.Case.VALID, "4924195509196","49241955091","Hi, there.");
		
		private UserCases.Case account;
		private String from, to, text;

		private Case(UserCases.Case account, String from, String to, String text)
		{
			this.to = to;
			this.from = from;
			this.text = text;
			
			this.account = account;
		}
		
		public UserCases.Case getAccount()
		{
			return this.account;
		}
		
		public String getFrom()
		{
			return this.from;
		}
		
		public String getTo()
		{
			return this.to;
		}
		
		public String text() 
		{
			return this.text;
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
			
			new InBoundTester(useCase).test();
		}
	}
	
	public static void runService()
	{
		Iterator<Case> cases = Case.caseIterator();
		
		while(cases.hasNext())
		{
			Case useCase = cases.next();
			
			System.out.print("Case name --> "+ useCase.name());
			System.out.print("API Response --> "+ new InBoundTester(useCase).invokeAPI());
		}
	}
}
