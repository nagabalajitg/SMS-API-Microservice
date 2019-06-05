package com.ms.sms.api.core.message;

import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.ms.sms.api.core.subscriber.Receiver;
import com.ms.sms.api.core.subscriber.Sender;
import com.ms.sms.api.db.DBConnector;
import com.ms.sms.api.utils.SMSUtils.Message;
import com.ms.sms.api.variables.SMSVariables;

public class InBoundMessage extends SMSMessage
{
	private static final List<String> CACHE_KEYS = Arrays.asList("STOP","STOP\n","STOP\r","STOP\r\n");
	private static final String NUMBER_NOT_PRESENT = Message.getFormattedString(new String[] {Message.TO}, Message.NUMBER_NOT_FOUND);
	
	private InBoundMessage(String message, Sender sender, Receiver receiver) throws Exception
	{
		super(message, sender, receiver);
	}
	
	public static InBoundMessage getInstance(SMSVariables variables) throws Exception
	{
		return new InBoundMessage(
									variables.getMessage(), 
									variables.getMetaInstance().getSender(), 
									variables.getMetaInstance().getReceiver()
								);
	}
	
	public JSONObject inBound() throws JSONException
	{
		JSONObject response = Message.getResponseJSON();
		
		if(getReceiver().isNumberPresentInAccount())
		{
			if(CACHE_KEYS.contains(getMessage()))
			{
				String key = getSender().getNumber() + "_" + getReceiver().getNumber() + "_STOP";
				
				DBConnector.redis().set(key, CACHE_VALUE);
				DBConnector.redis().expire(key, (ONE_HOUR * 4));
			}
			
			response.put(Message.MESSAGE, Message.INBOUND_SMS_OK);
		}
		else
		{
			response.put(Message.ERROR, NUMBER_NOT_PRESENT);
		}
		
		return response;
	}
}
