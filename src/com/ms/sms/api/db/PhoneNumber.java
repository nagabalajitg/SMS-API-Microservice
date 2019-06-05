package com.ms.sms.api.db;

public class PhoneNumber 
{
	private int id;
	private String phoneNumber;
	
	public PhoneNumber() {}
	
	public PhoneNumber(int id, String phoneNumber) 
	{
		this.id = id;
		this.phoneNumber = phoneNumber;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getPhoneNumber()
	{
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
	
	public boolean equals(Object obj) {
	      if (obj == null) return false;
	      if (!this.getClass().equals(obj.getClass())) return false;

	      PhoneNumber obj2 = (PhoneNumber)obj;
	      if((this.id == obj2.getId()) && (this.phoneNumber.equals(obj2.getPhoneNumber()))) {
	         return true;
	      }
	      return false;
	   }
	   
	   public int hashCode() {
	      int tmp = 0;
	      tmp = ( id + phoneNumber ).hashCode();
	      return tmp;
	   }
}
