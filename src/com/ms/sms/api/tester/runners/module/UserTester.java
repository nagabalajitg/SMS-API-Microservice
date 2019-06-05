package com.ms.sms.api.tester.runners.module;

import com.ms.sms.api.core.subscriber.User;
import com.ms.sms.api.exception.SMSAPIException;
import com.ms.sms.api.tester.runners.module.cases.UserCases;

public class UserTester 
{
	private UserCases.Case useCase;
	
	public UserTester(UserCases.Case useCase)
	{
		this.useCase = useCase;
	}
	
	public void test()
	{
		validateCredentials();
	}
	
	private void validateCredentials()
	{
		try
		{
			User.getInstance(useCase.getUserName(), useCase.getAuthtoken());
			System.out.println("Valid User !");
		}
		catch(Exception e)
		{
			String error;
			if(SMSAPIException.class.isInstance(e))
			{
				error = ((SMSAPIException)e).getError();
			}
			else
			{
				error = e.getMessage();
			}
			
			System.out.println("error --> "+ error);
		}
	}
}
