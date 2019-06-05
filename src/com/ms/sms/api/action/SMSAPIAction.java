package com.ms.sms.api.action;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import com.ms.sms.api.core.message.InBoundMessage;
import com.ms.sms.api.core.message.OutBoundMessage;
import com.ms.sms.api.utils.SMSUtils;

public class SMSAPIAction extends APIAction
{
	public void inbound() throws Exception
	{
		InBoundMessage sms = InBoundMessage.getInstance(SMSUtils.SMSVARIABLES.get());
		stream(sms.inBound());
	}
	
	public void outbound() throws Exception
	{
		OutBoundMessage sms = OutBoundMessage.getInstance(SMSUtils.SMSVARIABLES.get());
		stream(sms.outBound());
	}
	
	private void stream(JSONObject responseJson) throws IOException
	{
		response.setContentType(SMSUtils.ContentType.JSON.getContentType());
		
		PrintWriter writer = response.getWriter();
		writer.println(responseJson.toString());
		writer.close();
	}
}
