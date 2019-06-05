package com.ms.sms.api.tester.runners.module.cases;

public interface MessageBoundCases 
{
	interface AbstractCase
	{ 
		public String name();
		public String text();
		public String getTo();
		public String getFrom();
		public String toString();
		public UserCases.Case getAccount();
	}
}
