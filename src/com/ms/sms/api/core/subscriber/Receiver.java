package com.ms.sms.api.core.subscriber;

public class Receiver extends SubscriberNumber 
{
	private Receiver(User user, String phoneNumber) throws Exception 
	{
		super(user, phoneNumber);
	}
	
	public static Receiver getInstance(User user, String phoneNumber) throws Exception
	{
		return new Receiver(user, phoneNumber);
	}
	
	protected SubscriberType getSubscriberType() 
	{
		return SubscriberType.RECEIVER;
	}
}
