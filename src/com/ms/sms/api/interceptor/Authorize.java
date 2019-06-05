package com.ms.sms.api.interceptor;


import com.ms.sms.api.core.subscriber.Receiver;
import com.ms.sms.api.core.subscriber.Sender;
import com.ms.sms.api.core.subscriber.User;
import com.ms.sms.api.utils.SMSUtils;
import com.ms.sms.api.variables.SMSVariables;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class Authorize implements Interceptor  
{
	private static final long serialVersionUID = 1L;

	@Override 
	public void destroy() {}

	@Override
	public void init() {}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception 
	{
		authorize();
		return actionInvocation.invoke();
	}
	
	private void authorize() throws Exception
	{
		SMSVariables variables = SMSUtils.SMSVARIABLES.get();
		User user = User.getInstance(variables.getUserName(), variables.getAuthID());
		
		variables.getMetaInstance().setUser(user);
		variables.getMetaInstance().setSender(Sender.getInstance(user, variables.getSenderNumber()));
		variables.getMetaInstance().setReceiver(Receiver.getInstance(user, variables.getReceiverNumber()));
	}
}
