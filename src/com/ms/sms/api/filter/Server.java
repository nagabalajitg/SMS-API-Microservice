package com.ms.sms.api.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ms.sms.api.utils.SMSUtils;

public class Server implements Filter 
{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		String url = ((HttpServletRequest)arg0).getRequestURI();
		try
		{
			SMSUtils.SMSVARIABLES.set(null);
			Logger.getGlobal().severe(" Request Begin URL --> " + url);
			arg2.doFilter(arg0, arg1);
		}
		catch(Exception e)
		{
			Logger.getGlobal().log(Level.SEVERE,"URL --> " + url + " Message : " + e.getMessage(),e);
		}
		finally 
		{
			SMSUtils.SMSVARIABLES.set(null);
			Logger.getGlobal().severe(" Request Ends URL --> " + url);
		}
	}
}
