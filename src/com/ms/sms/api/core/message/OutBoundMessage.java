package com.ms.sms.api.core.message;

import org.json.JSONException;
import org.json.JSONObject;

import com.ms.sms.api.core.subscriber.Receiver;
import com.ms.sms.api.core.subscriber.Sender;
import com.ms.sms.api.db.DBConnector;
import com.ms.sms.api.utils.SMSUtils.Message;
import com.ms.sms.api.variables.SMSVariables;

public class OutBoundMessage extends SMSMessage 
{
	public static final int MAX_SMS_PER_24_HOURS = 50;
	private static final String NUMBER_NOT_PRESENT = Message.getFormattedString(new String[] {Message.FROM}, Message.NUMBER_NOT_FOUND);
	
	private OutBoundMessage(String message, Sender sender, Receiver receiver) throws Exception 
	{
		super(message, sender, receiver);
	}
	
	public static OutBoundMessage getInstance(SMSVariables variables) throws Exception
	{
		return new OutBoundMessage(
									variables.getMessage(), 
									variables.getMetaInstance().getSender(), 
									variables.getMetaInstance().getReceiver()
								);
	}
	
	public JSONObject outBound() throws JSONException
	{
		JSONObject response = Message.getResponseJSON();
		
		if(getSender().isNumberPresentInAccount())
		{
			int smsCount = 0;
			String key = getSender().getNumber() + "_" + getReceiver().getNumber();
			String stopKey= key + "_STOP";
			String cacheValue = DBConnector.redis().get(stopKey);
			
			if(CACHE_VALUE.equals(cacheValue)) 
			{
				String error = Message.getFormattedString(	new String[]
															{ 
																getSender().getNumber(), 
																getReceiver().getNumber() 
															}, 
															Message.NUMBER_BLOCKER
														);
				response.put(Message.ERROR, error);
			}
			else
			{	
				String smsCountKey= key + "_COUNT";
				boolean isDailyLimit = Boolean.FALSE;
				cacheValue = DBConnector.redis().get(smsCountKey);
				
				if(cacheValue != null)
				{
					smsCount = Integer.parseInt(cacheValue);
					
					if(smsCount >= MAX_SMS_PER_24_HOURS)
					{
						isDailyLimit = Boolean.TRUE;
					}
					else
					{
						smsCount ++;
					}
				}
				else
				{
					smsCount ++;
					DBConnector.redis().expire(smsCountKey, (ONE_HOUR * 24));
				}
				
				DBConnector.redis().set(smsCountKey, String.valueOf(smsCount));
				
				if(isDailyLimit)
				{
					String error = Message.getFormattedString(	new String[]
																{ 
																	getSender().getNumber(), 
																}, 
																Message.LIMIT_REACHED
															);
					response.put(Message.ERROR, error);
				}
				else
				{
					response.put(Message.MESSAGE, Message.OUTBOUND_SMS_OK);
				}
			}
		}
		else
		{
			response.put(Message.ERROR, NUMBER_NOT_PRESENT);
		}
		
		return response;
	}
}
