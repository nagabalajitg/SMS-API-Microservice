package com.ms.sms.api.variables;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.ms.sms.api.core.subscriber.Receiver;
import com.ms.sms.api.core.subscriber.Sender;
import com.ms.sms.api.core.subscriber.User;

public class SMSVariables 
{
	private String authID;
	private String userName;
	
	private String message;
	private String senderNumber;
	private String receiverNumber;
	
	private Meta meta;
	
	public SMSVariables(HttpServletRequest request)
	{
		this.authID = StringUtils.isEmpty(request.getHeader("auth_id")) ? null : request.getHeader("auth_id");
		this.userName = StringUtils.isEmpty(request.getHeader("username")) ? null : request.getHeader("username");
		
		this.message = StringUtils.isEmpty(request.getParameter("text")) ? null : request.getParameter("text");
		this.senderNumber = StringUtils.isEmpty(request.getParameter("from")) ? null : request.getParameter("from");
		this.receiverNumber = StringUtils.isEmpty(request.getParameter("to")) ? null : request.getParameter("to");
	}
	
	public SMSVariables(Map<String,String> variables)
	{
		this.authID = StringUtils.isEmpty(variables.get("authtoken")) ? null : variables.get("authtoken");
		this.userName = StringUtils.isEmpty(variables.get("userName")) ? null : variables.get("userName");
		
		this.message = StringUtils.isEmpty(variables.get("text")) ? null : variables.get("text");
		this.senderNumber = StringUtils.isEmpty(variables.get("from")) ? null : variables.get("from");
		this.receiverNumber = StringUtils.isEmpty(variables.get("to")) ? null : variables.get("to");
	}

	public String getUserName()
	{
		return this.userName;
	}
	
	public String getAuthID()
	{
		return this.authID;
	}
	
	public String getMessage()
	{
		return this.message;
	}
	
	public String getSenderNumber()
	{
		return this.senderNumber;
	}
	
	public String getReceiverNumber()
	{
		return this.receiverNumber;
	}
	
	public Meta getMetaInstance()
	{
		if(this.meta == null) 
		{ 
			this.meta = new Meta();
		}
		return this.meta;
	}
	
	public class Meta
	{
		private User user = null;
		private Sender sender = null;
		private Receiver receiver = null;
		
		Meta(){}
		
		public User getUser()
		{
			return this.user;
		}
		
		public Sender getSender()
		{
			return this.sender;
		}
		
		public Receiver getReceiver()
		{
			return this.receiver;
		}
		
		public void setReceiver(Receiver receiver)
		{
			if(this.getReceiver() == null)
			{
				this.receiver = receiver;
			}
		}
		
		public void setUser(User user)
		{
			if(this.getUser() == null)
			{
				this.user = user;
			}
		}
		
		public void setSender(Sender sender)
		{
			if(this.getSender() == null)
			{
				this.sender = sender;
			}
		}
	}
}
