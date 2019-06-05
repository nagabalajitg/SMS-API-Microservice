package com.ms.sms.api.exception;

import java.util.Objects;

import org.json.JSONException;
import org.json.JSONObject;

public class SMSAPIException extends Exception 
{
	private String error;
	
	public SMSAPIException(String message, String error)
	{
		super(message);
		
		Objects.requireNonNull(error);
		Objects.requireNonNull(message);
		this.error = error;
	}
	
	public String getError()
	{
		return this.error;
	}
	
	public JSONObject getErrorJSON() throws JSONException
	{
		return new JSONObject()
				.put("message", getMessage())
				.put("error", getError());
	}
}