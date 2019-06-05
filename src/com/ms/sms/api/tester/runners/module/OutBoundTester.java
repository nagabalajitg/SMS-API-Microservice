package com.ms.sms.api.tester.runners.module;

import com.ms.sms.api.core.message.OutBoundMessage;
import com.ms.sms.api.db.DBConnector;
import com.ms.sms.api.exception.SMSAPIException;
import com.ms.sms.api.tester.runners.module.cases.MessageBoundCases.AbstractCase;
import com.ms.sms.api.tester.runners.module.cases.OutBoundCases.Case;
import com.ms.sms.api.utils.SMSUtils;

public class OutBoundTester extends MessageBoundTester
{
	protected static final String APIURL = "/outbound/sms/";
	
	public OutBoundTester(AbstractCase useCase)
	{
		super(useCase);
	}
	
	public void test()
	{
		checkOutBound();
	}
	
	public String invokeAPI()
	{
		return this.invokeURL();
	}
	
	private void checkOutBound()
	{
		try
		{
			initVariables();
			System.out.println("OutBoud Response --> "+ OutBoundMessage.getInstance(getVaribles()).outBound());
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
	
	private static void specialCase()
	{
		Case useCase = Case.DAILY_LIMIT;
		String key = useCase.getFrom() + "_" + useCase.getTo();
		String cacheValue = DBConnector.redis().get(key);
		
		if(cacheValue != null)
		{
			DBConnector.redis().set(key, String.valueOf(OutBoundMessage.MAX_SMS_PER_24_HOURS));
			
			System.out.print("Case name --> "+ useCase.name());
			new OutBoundTester(useCase).test();
		}
	}

	@Override
	protected String getAPIURL() 
	{
		return APIURL;
	}
}