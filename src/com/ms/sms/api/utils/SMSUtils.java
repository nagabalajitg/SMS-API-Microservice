package com.ms.sms.api.utils;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.ms.sms.api.variables.SMSVariables;

public class SMSUtils 
{
	public static final ThreadLocal<SMSVariables> SMSVARIABLES = new ThreadLocal();
	
	public static class Message
	{
		
		public static final String UNKNOWN_ERROR = "unknown failure";
		public static final String MISSING_PARAM = "{0} is missing";
		public static final String INVALID_PARAM = "{0} is invalid";
		public static final String INVALID_HTTP_URL = "in-valid HTTP url";
		public static final String INVALID_HTTP_METHOD = "in-valid HTTP method";
		public static final String LIMIT_REACHED = "limit reached for from {0}";
		public static final String NUMBER_NOT_FOUND = "{0} parameter not found";
		public static final String NUMBER_BLOCKER = "sms from {0} to {1} blocked by STOP request";
		
		public static final String INBOUND_SMS_OK = "inbound sms ok";
		public static final String OUTBOUND_SMS_OK = "outbound sms ok";
		
		public static final String ERROR = "error";
		public static final String MESSAGE = "message";
		
		public static final String TO = "to";
		public static final String FROM = "from";
		public static final String TEXT = "text";
		public static final String USERNAME = "username";
		public static final String AUTHTOKEN = "auth_id";
				
		public static String getFormattedString(String[] values,String formatText)
		{
			return MessageFormat.format(formatText,values);	
		}
		
		public static JSONObject getResponseJSON() throws JSONException
		{
			return new JSONObject().put(MESSAGE, StringUtils.EMPTY).put(ERROR, StringUtils.EMPTY);
		}
	}
	
	public enum HTTPMethods
	{	
		POST("POST"),
		GET("GET");
		
		private String method;
		private HTTPMethods(String method)
		{
			this.method = method;
		}
		
		public String getMethod()
		{
			return this.method;
		}
	}
	
	public enum ContentType
	{
		JSON("application/json;charset=UTF-8");
		
		private String contentType;
		private ContentType (String contentType)
		{
			this.contentType = contentType;
		}
		
		public String getContentType()
		{
			return this.contentType;
		}
	}
}
