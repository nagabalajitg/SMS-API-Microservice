package com.ms.sms.api.interceptor;

import org.apache.struts2.ServletActionContext;

import com.ms.sms.api.utils.SMSUtils;
import com.ms.sms.api.variables.SMSVariables;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class Variables implements Interceptor 
{
	@Override
	public void destroy() {}

	@Override
	public void init() {}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception 
	{
		SMSUtils.SMSVARIABLES.set(
									new SMSVariables(ServletActionContext.getRequest())
								 );
		return invocation.invoke();
	}

}
