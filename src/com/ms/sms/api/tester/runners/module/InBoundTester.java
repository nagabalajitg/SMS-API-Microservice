package com.ms.sms.api.tester.runners.module;

import com.ms.sms.api.core.message.InBoundMessage;
import com.ms.sms.api.exception.SMSAPIException;
import com.ms.sms.api.tester.runners.module.cases.MessageBoundCases.AbstractCase;
import com.ms.sms.api.utils.SMSUtils;

public class InBoundTester extends MessageBoundTester
{
	protected static final String APIURL = "/inbound/sms/";
	
	public InBoundTester(AbstractCase useCase)
	{
		super(useCase);
	}
	
	public void test()
	{
		checkInBound();
	}
	
	public String invokeAPI()
	{
		return this.invokeURL();
	}
	
	private void checkInBound()
	{
		try
		{
			initVariables();
			System.out.println("OutBoud Response --> "+ InBoundMessage.getInstance(getVaribles()).inBound());
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
		finally
		{
			SMSUtils.SMSVARIABLES.set(null);
		}
	}

	protected String getAPIURL() 
	{
		return APIURL;
	}
}