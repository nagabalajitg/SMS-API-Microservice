package com.ms.sms.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ms.sms.api.utils.SMSUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class HTTPMethodInterceptor implements Interceptor
{

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse rep =  ServletActionContext.getResponse();
		
		if(!SMSUtils.HTTPMethods.POST.getMethod().equals(req.getMethod()))
		{
			rep.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
//			req.getRequestDispatcher("/Error4xxHandler").forward(req, rep);
//			req.
			return null;
		}
		
		return arg0.invoke();
	}

	

}
