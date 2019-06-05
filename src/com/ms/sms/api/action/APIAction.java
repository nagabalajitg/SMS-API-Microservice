package com.ms.sms.api.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class APIAction extends ActionSupport implements ServletRequestAware, ServletResponseAware 
{
	private static final long serialVersionUID = 1L;
	
	
	protected HttpServletRequest request = null;
	protected HttpServletResponse response = null;

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
