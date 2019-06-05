package com.ms.sms.api.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import redis.clients.jedis.Jedis;  

public class DBConnector 
{
	private DBConnector() {}
	
	/*@Deprecated
	public static SessionFactory sessionFactory()
	{
		return Hibernate.getSessionFactory();
	} */
	
	public static Connection getPGConnection()
	{
		return DiskDB.getConnection();
	}
	
	public static Jedis redis()
	{
		return InMemoryDB.getRedisInstance();
	}
	
/*	private static class Hibernate extends DiskDB
	{
		private static final SessionFactory SESSIONFACTORY;
		
		static 
		{
			StandardServiceRegistry dbRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
			Metadata sessionMeta = new MetadataSources(dbRegistry).getMetadataBuilder().build(); 
			SESSIONFACTORY = sessionMeta.getSessionFactoryBuilder().build();
		}
		
		
		static SessionFactory getSessionFactory()
		{
			return SESSIONFACTORY;
		}
	} */
	
	public static class TablesAndColumns
	{
		public static final String PHONE_NUMBER = "phone_number";
		public static final String NUMBER = "number";
		public static final String ACCOUNT_ID = "account_id";
		
		public static final String ID = "id";
		public static final String ACCOUNT = "account";
		public static final String AUTH_ID = "auth_id";
		public static final String USERNAME = "username";
		
		
	}
	
	private static class DiskDB
	{
		private static Connection CONNECTION = null;
		
		static
		{
			try 
			{
				CONNECTION = DriverManager.getConnection("jdbc:postgresql://localhost:5431/api","postgres","");
			} 
			catch (SQLException e) 
			{
				Logger.getGlobal().log(Level.SEVERE, e.getMessage(),e);
			}
		}
		
		static Connection getConnection()
		{
			return CONNECTION;
		}
	}
	
	private static class InMemoryDB
	{
		private static final Jedis JEDIS = new Jedis("localhost");
		
		static Jedis getRedisInstance()
		{
			return JEDIS;
		}
	}
}
