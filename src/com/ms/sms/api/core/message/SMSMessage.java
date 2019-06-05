package com.ms.sms.api.core.message;

import org.apache.commons.lang.StringUtils;

import com.ms.sms.api.core.subscriber.Receiver;
import com.ms.sms.api.core.subscriber.Sender;
import com.ms.sms.api.exception.SMSAPIException;
import com.ms.sms.api.utils.SMSUtils;

public abstract class SMSMessage implements Message
{
	private Sender sender = null;
	private Receiver receiver = null;
	
	private String message = null;
	private static final int MIN_LENGTH = 1;
	private static final int MAX_LENGTH = 120;
	
	protected static final int ONE_HOUR = 60 * 60; //Seconds
	protected static final String CACHE_VALUE = "STOP";
	
	private static final String INVALID_PARAM;
	private static final String MISSING_PARAM;
	
	static 
	{
		INVALID_PARAM = SMSUtils.Message.getFormattedString(
								new String[] {SMSUtils.Message.TEXT},
								SMSUtils.Message.INVALID_PARAM
							);
		
		MISSING_PARAM = SMSUtils.Message.getFormattedString(
								new String[] {SMSUtils.Message.TEXT},
								SMSUtils.Message.MISSING_PARAM
							);
	}
	
	
	protected SMSMessage(String message, Sender sender, Receiver receiver) throws Exception
	{
		this.sender = sender;
		this.message = message;
		this.receiver = receiver;
		this.validateData();
	}
	
	public String getMessage() 
	{
		return this.message;
	}
	
	public Sender getSender() 
	{
		return this.sender;
	}
	
	public Receiver getReceiver() 
	{
		return this.receiver;
	}
	
	public void validateData() throws Exception
	{
		Exception exception = null;
		
		if(getMessage() == null)
		{
			exception = new SMSAPIException(StringUtils.EMPTY, MISSING_PARAM);
		}
		else if(getMessage().length() < MIN_LENGTH || getMessage().length() > MAX_LENGTH )
		{
			exception = new SMSAPIException(StringUtils.EMPTY, INVALID_PARAM);
		}
		
		if(exception != null) { throw exception;}
	}
}
