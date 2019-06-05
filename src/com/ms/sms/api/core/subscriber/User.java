package com.ms.sms.api.core.subscriber;

import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.ms.sms.api.db.Account;
import com.ms.sms.api.db.DBConnector;
import com.ms.sms.api.db.DBConnector.TablesAndColumns;
import com.ms.sms.api.exception.SMSAPIException;
import com.ms.sms.api.exception.UnAuthorizedException;

public class User
{
	private String userName;
	private String authtoken;
	
	private static final String CREDENTIALS_ERROR = "Post credentials properly";
	private static final String INVALID_CREDENTIALS_ERROR = "Either username or auth_id or both are wrong";
	private static final Logger LOGGER = Logger.getLogger(User.class.getName());
	
	private User(String userName, String authtoken) throws Exception
	{
		this.userName = userName;
		this.authtoken = authtoken;
		this.validate();
	}
	
	public static User getInstance(String userName, String authtoken) throws Exception
	{
		return new User(userName, authtoken);
	}
	
	public String getUserName()
	{
		return this.userName;
	}
	
	private String getAuthtoken()
	{
		return this.authtoken;
	}
	
	public void validate() throws Exception
	{
		Exception exception = null;
		boolean userNameEmpty = StringUtils.isEmpty(getUserName());
		boolean authKeyNameEmpty = StringUtils.isEmpty(getAuthtoken());
		
		if(userNameEmpty || authKeyNameEmpty)
		{
			exception = new SMSAPIException(StringUtils.EMPTY, CREDENTIALS_ERROR);
		}
		else 
		{
			try
			{
				
				String sql = "select * from " + TablesAndColumns.ACCOUNT +
							" where " + TablesAndColumns.USERNAME + "='"+ getUserName() +
							"' and " + TablesAndColumns.AUTH_ID + "='" + getAuthtoken() + "';";
				
				ResultSet set = DBConnector.getPGConnection().createStatement().executeQuery(sql);
				
				
				if(!set.next())
				{
					throw new UnAuthorizedException(StringUtils.EMPTY, INVALID_CREDENTIALS_ERROR);
				}
				set.close();
			}
			catch(Exception e)
			{
				exception = e;
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		
		if(exception != null) { throw exception;}
	}
	
	
	/*
	 * 
	 * session = DBConnector.sessionFactory().openSession();
				Transaction transaction = session.beginTransaction();
				
				
				List account = session.createCriteria(Account.class)
										.add(Restrictions.and( 
														Restrictions.eq("userName", getUserName()), 
														Restrictions.eq("auth_id", getAuthtoken())
													)
										).list();
										
										if(account.isEmpty())
				{
					throw new UnAuthorizedException(StringUtils.EMPTY, INVALID_CREDENTIALS_ERROR);
				}
				
				transaction.commit();
	 */
}
