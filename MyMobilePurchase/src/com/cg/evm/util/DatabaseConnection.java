package com.cg.evm.util;


	
	import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;


	public class DatabaseConnection 
	{
		static Logger lg=Logger.getLogger(DatabaseConnection.class);
		
		public static Connection getConnection()
		{
			String url="";
			String username="";
			String pwd="";
			String driver="";
			Connection con=null;
			
			try 
			{
				//loading properties from config.properties file
				InputStream in= new FileInputStream("./resources/jdbc.properties");
				Properties pr= new Properties();
				pr.load(in);
				url=pr.getProperty("url");
				username=pr.getProperty("username");
				pwd=pr.getProperty("pwd");
				driver=pr.getProperty("driver");
				Class.forName(driver);
				con= DriverManager.getConnection(url, username, pwd);
				if(con!=null)
					lg.info("Database Connected");
				
					//System.out.println("Database connected");
				
			}
			catch (IOException e) 
			{
				System.out.println(e.getMessage());
			}
			catch (ClassNotFoundException e) 
			{
				System.out.println("Error in loading class");
				e.printStackTrace();
			}
			catch (SQLException e) 
			{
				System.out.println("Error in connection");
				e.printStackTrace();
			}
			return con;
		}
		
			public static void main(String[] args) {
				@SuppressWarnings("unused")
				Connection con=getConnection();
			}
		
	}


