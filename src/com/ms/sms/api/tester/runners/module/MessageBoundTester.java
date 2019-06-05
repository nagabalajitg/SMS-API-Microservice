package com.ms.sms.api.tester.runners.module;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.ms.sms.api.core.subscriber.Receiver;
import com.ms.sms.api.core.subscriber.Sender;
import com.ms.sms.api.core.subscriber.User;
import com.ms.sms.api.tester.runners.module.cases.UserCases;
import com.ms.sms.api.tester.runners.module.cases.MessageBoundCases.AbstractCase;
import com.ms.sms.api.utils.SMSUtils.HTTPMethods;
import com.ms.sms.api.variables.SMSVariables;

public abstract class MessageBoundTester 
{
	private String to;
	private String from;
	private String message;
	private UserCases.Case account;
	
	private SMSVariables variables;
	
	private static final String DOMAIN = "http://localhost:8080";
	private static final Logger LOGGER = Logger.getLogger(MessageBoundTester.class.getName());
	
	protected MessageBoundTester(AbstractCase useCase)
	{
		account = useCase.getAccount();
		to = useCase.getTo();
		from = useCase.getFrom();
		message = useCase.text();
	}
	
	protected abstract String getAPIURL();
	
	protected void initVariables() throws Exception
	{
		Map<String,String> map = new HashMap<>();
		
		map.put("userName", account.getUserName());
		map.put("authtoken", account.getAuthtoken());
		
		map.put("to", to);
		map.put("from", from);
		map.put("text", message);
		
		variables = new SMSVariables(map);
		User user = User.getInstance(account.getUserName(), account.getAuthtoken());
		Sender.getInstance(user, from);
		
		variables.getMetaInstance().setUser(user);
		variables.getMetaInstance().setSender(Sender.getInstance(user, from));
		variables.getMetaInstance().setReceiver(Receiver.getInstance(user, to));
	}
	
	protected SMSVariables getVaribles()
	{
		return this.variables;
	}
	
	protected String invokeURL()
	{
		InputStream inputStream = null;
		String result = StringUtils.EMPTY;
		
		try
		{
			HttpURLConnection connection = (HttpURLConnection)new URL(getHTTPURL()).openConnection();
			
			connection.setRequestProperty("username", account.getUserName());
			connection.setRequestProperty("auth_id", account.getAuthtoken());
			connection.setRequestMethod(HTTPMethods.POST.getMethod());
			inputStream = connection.getInputStream();
			
			result = IOUtils.toString(inputStream);
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		
		return result;
	}
	
	private String getHTTPURL()
	{
		return new StringBuilder()
				.append(DOMAIN).append(getAPIURL())
				.append("?from=").append(from)
				.append("&to=").append(to)
				.append("&text=").append(message)
				.toString();
	}
}
