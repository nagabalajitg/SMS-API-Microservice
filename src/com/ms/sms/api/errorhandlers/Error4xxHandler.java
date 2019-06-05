package com.ms.sms.api.errorhandlers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.ms.sms.api.utils.SMSUtils.Message;

public class Error4xxHandler extends APIErrorHandler
{
	private static final Logger LOGGER = Logger.getLogger(Error4xxHandler.class.getName());
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		try
		{
			int code = res.getStatus();
			String error = Message.UNKNOWN_ERROR;
			JSONObject responseJSON = Message.getResponseJSON();
			
			if(code == HttpServletResponse.SC_NOT_FOUND)
			{
				error =  Message.INVALID_HTTP_URL;
			}
			else if (code == HttpServletResponse.SC_METHOD_NOT_ALLOWED)
			{
				error =  Message.INVALID_HTTP_METHOD;
			}
			
			writeJSON(
						res, 
						responseJSON.put(Message.ERROR, error)
					);
		}
		catch (JSONException e) 
		{
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			writeJSON(res, UNKNOW_ERROR);
		}
	}

	protected void doHead(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		doPost(req, res);
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		doPost(req, res);
	}
	
	protected void doPatch(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		doPost(req, res);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		doPost(req, res);
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		doPost(req, res);
	}
}
