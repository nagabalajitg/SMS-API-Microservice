package com.ms.sms.api.errorhandlers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.ms.sms.api.exception.SMSAPIException;
import com.ms.sms.api.exception.UnAuthorizedException;
import com.ms.sms.api.utils.SMSUtils;
import com.ms.sms.api.utils.SMSUtils.Message;

public class ExceptionHandler extends APIErrorHandler 
{
	private static final Logger LOGGER = Logger.getLogger(ExceptionHandler.class.getName());
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		JSONObject responseJSON = UNKNOW_ERROR;
		try
		{
			Exception ex = (Exception) req.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
			if(ex != null)
			{
				if(SMSAPIException.class.isInstance(ex))
				{
					SMSAPIException smsException = (SMSAPIException) ex;
					responseJSON = smsException.getErrorJSON();
					
					
					if(UnAuthorizedException.class.isInstance(ex))
					{
						res.setStatus(HttpServletResponse.SC_FORBIDDEN);
					}
					else if(Message.UNKNOWN_ERROR.equals(smsException.getError()))
					{
						res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					}
				}
			}
			
			writeJSON(res, responseJSON);
		}
		catch (JSONException e) 
		{
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			writeJSON(res, UNKNOW_ERROR);
		}
	}

	protected void doHead(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		error405(res);
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		error405(res);
	}
	
	protected void doPatch(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		error405(res);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("adsadsada");
		LOGGER.severe("asdsadsadsadasd");
		if(req.getAttribute(RequestDispatcher.ERROR_EXCEPTION) != null) 
		{
			doPost(req, res);
		} 
		else
		{
			error405(res);

		}
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		error405(res);
	}
	
	private void error405(HttpServletResponse res) throws IOException
	{
		try 
		{
			res.setStatus(405);
			writeJSON(
						res, 
						Message.getResponseJSON().put(Message.ERROR, Message.INVALID_HTTP_METHOD)
					);
		} 
		catch (JSONException e) 
		{
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			writeJSON(res, UNKNOW_ERROR);
		}
	}
}
