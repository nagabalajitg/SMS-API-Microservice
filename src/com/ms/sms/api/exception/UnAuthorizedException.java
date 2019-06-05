package com.ms.sms.api.exception;

public class UnAuthorizedException extends SMSAPIException {

	private static final long serialVersionUID = 1L;
	
	public UnAuthorizedException(String message, String error) 
	{
		super(message, error);
	}
}
