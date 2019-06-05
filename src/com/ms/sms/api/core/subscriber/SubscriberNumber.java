package com.ms.sms.api.core.subscriber;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import com.ms.sms.api.db.DBConnector;
import com.ms.sms.api.db.DBConnector.TablesAndColumns;
import com.ms.sms.api.exception.SMSAPIException;
import com.ms.sms.api.utils.SMSUtils.Message;

public abstract class SubscriberNumber implements PhoneNumber 
{
	private User user = null;
	private String phoneNumber = null;
	private static final int MIN_LENGTH = 6;
	private static final int MAX_LENGTH = 16;
	
	private static final Logger LOGGER = Logger.getLogger(SubscriberNumber.class.getName());
	
	protected enum SubscriberType
	{
		SENDER("from"),
		RECEIVER("to");
		
		private String requestParamName;
		
		private SubscriberType(String requestParamName)
		{
			this.requestParamName = requestParamName;
		}
		
		public String getParamName()
		{
			return this.requestParamName;
		}
	}
	
	protected SubscriberNumber(User user,String phoneNumber) throws Exception
	{
		this.user = user;
		this.phoneNumber = phoneNumber;
		validateData();
	}
	
	protected abstract SubscriberType getSubscriberType();
	
	public String getNumber()
	{
		return this.phoneNumber;
	}
	
	public User getUserName()
	{
		return this.user;
	}
	
	
	public void validateData() throws Exception
	{
		Exception exception = null;
		String paramName = getSubscriberType().getParamName();
		
		if(getNumber() == null)
		{
			String error =  Message.getFormattedString(
											new String[] {paramName}, 
											Message.MISSING_PARAM
										);
			
			exception = new SMSAPIException(StringUtils.EMPTY, error);
		}
		else 
		{	
			boolean invalid = Boolean.FALSE;
			
			if(getNumber().length() < MIN_LENGTH || getNumber().length() > MAX_LENGTH )
			{
				invalid = Boolean.TRUE;
			}
			else if (!StringUtils.isNumeric(getNumber())) 
			{
				invalid = Boolean.TRUE;
			}
			
			if(invalid)
			{
				String error =  Message.getFormattedString(
										new String[] {paramName}, 
										Message.INVALID_PARAM
									);

				exception = new SMSAPIException(StringUtils.EMPTY, error);
			}
		}
		
		
		if(exception != null) { throw exception;}
	}
	
	public boolean isNumberPresentInAccount()
	{
		boolean isNumberPresent = Boolean.FALSE;
		
		try
		{
			String sql = "select * from " + TablesAndColumns.ACCOUNT + 
					" inner join " + TablesAndColumns.PHONE_NUMBER + " on " +
					TablesAndColumns.ACCOUNT + "." + TablesAndColumns.ID + "=" +
					TablesAndColumns.PHONE_NUMBER + "." + TablesAndColumns.ACCOUNT_ID +
					" where " + TablesAndColumns.USERNAME + "='" + getUserName().getUserName() +"' and " +
					TablesAndColumns.NUMBER + "='" + getNumber() +"';";
//			Logger.getGlobal().severe("SQL -->" +sql);
			ResultSet set = DBConnector.getPGConnection().createStatement().executeQuery(sql);
			
			if(set.next())
			{
				isNumberPresent = Boolean.TRUE;
			}
		}	
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		
		return isNumberPresent;
	}
	
	/**
	 * Session session = null;
		Transaction transaction = null;
	 * session = DBConnector.sessionFactory().openSession();
			transaction = session.beginTransaction();
			
			List account = session.createCriteria(Account.class)
									.add(Restrictions.eq("userName", user.getUserName()))
									.createCriteria(PhoneNumber.class.getSimpleName())
									.add(Restrictions.eq("number", getNumber()))
									.list();
			if(!account.isEmpty())
			{
				isNumberPresent = Boolean.TRUE;
			}
			
			transaction.commit();
		}
	 * 
	 * finally
		{
			if(session != null)
			{
				session.close(); 
			}
		}
	 * 
	 * */
}
