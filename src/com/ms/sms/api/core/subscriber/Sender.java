package com.ms.sms.api.core.subscriber;

public class Sender extends SubscriberNumber 
{
	private Sender(User user, String phoneNumber) throws Exception 
	{
		super(user, phoneNumber);
	}
	
	public static Sender getInstance(User user, String phoneNumber) throws Exception
	{
		return new Sender(user, phoneNumber);
	}
	
	protected SubscriberType getSubscriberType() 
	{
		return SubscriberType.SENDER;
	}
}
