package com.ms.sms.api.db;

import java.util.Set;

public class Account 
{
	private int id;
	private String auth_id;
	private String userName;
	private Set<PhoneNumber> PhoneNumber;
	
	public Account() {}
	
	public Account(int id, String auth_id, String userName) 
	{
		this.id = id;
		this.auth_id = auth_id;
		this.userName = userName;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getauth_id()
	{
		return this.auth_id;
	}
	
	public void setauth_id(String auth_id)
	{
		this.auth_id = auth_id;
	}
	
	public String getUserName()
	{
		return this.userName;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public Set<PhoneNumber> getPhoneNumber()
	{
		return this.PhoneNumber;
	}
	
	public void setPhoneNumber(Set<PhoneNumber> phoneNumbers)
	{
		this.PhoneNumber = phoneNumbers;
	}
}
