package com.ms.sms.api.errorhandlers;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.ms.sms.api.utils.SMSUtils;
import com.ms.sms.api.utils.SMSUtils.Message;

public abstract class APIErrorHandler extends HttpServlet
{
	protected static JSONObject UNKNOW_ERROR;
	protected static final String CONTENT_TYPE = "application/json;charset=UTF-8";
	
	static
	{
		try 
		{
			UNKNOW_ERROR = Message.getResponseJSON().put("error", Message.UNKNOWN_ERROR);
		} 
		catch (JSONException e) 
		{
			UNKNOW_ERROR = new JSONObject();
		}
	}
	
	protected void writeJSON(HttpServletResponse res, JSONObject responseJSON) throws IOException
	{
		res.setContentType(SMSUtils.ContentType.JSON.getContentType());
		res.getWriter().write(responseJSON.toString());
		res.getWriter().close();
	}
}
