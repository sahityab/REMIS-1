package com.prop.mnt.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBConnection {
	private static final Logger log = LoggerFactory.getLogger(DBConnection.class);
	private static DBConnection dbCon;
	private Connection con;
	
    private DBConnection(){
    }
	
	public static synchronized DBConnection getInstance() throws Exception{
		if(dbCon == null){
			dbCon = new DBConnection();
		}
		return dbCon;
	}
	
	public Connection getLocalConnection()throws SQLException
	{		
		try{
			log.info("getConnection..");
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/remisDS");
			con = ds.getConnection();			
			log.info("getConnection..:"+con);
		}catch(NamingException e)
		{
			log.info("connection exception :"+e);
		}
		return con;
	}
	public void returnConnection(Connection con, Statement stmt)
	{
		try{
			if(stmt != null)
				stmt.close();
			if(con != null)
				con.close();
		}catch(SQLException e)
		{
			log.info("connection exception while closing :"+e);
		}
	}
	
	public static Connection connection= null;
	public static Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); 
			//String url="dbc:odbc:ShoeBilling";
			//ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
			String serverName ="localhost";// bundle.getString("hostname");
			String mydatabase ="remis";// bundle.getString("databasename"); jdbc:mysql://localhost:3306/remis
			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
			String username = "root";//bundle.getString("databaseUsername");
			String password = "admin";//bundle.getString("databasePassword");
			//connection = DriverManager.getConnection("jdbc:mysql://localhost/jcomp1_gemscompdb","jcomp1_gems","gems123");
			connection = DriverManager.getConnection(url, username, password);
			
			//System.out.println("driver");
		}
		catch(Exception e)
		{
			System.out.println("erorr in driver"+e);
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void closeConnection(Connection con, Statement stmt,ResultSet rs)
	{
		try{
			if(stmt != null) stmt.close();
			
			if(con != null) con.close();
			
			if(rs!=null) rs.close();
		}catch(SQLException e)
		{
			log.info("connection exception while closing :"+e);
		}
	}
}

