package com.tevonetwork.tevoapi.API.MySQL;

import java.sql.SQLException;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.Core.LogLevel;

public class SQLRunnable implements Runnable{
	
	private String query;
	private String classname;
	private TevoAPI main = TevoAPI.getInstance();
	private SQLManager sql = main.getSQLManager();
	
	public SQLRunnable(String query, String classname)
	{
		this.query = query;
		this.classname = classname;
	}
	
	
	@Override
	public void run()
	{
		if (query != null)
		{
			try
			{
				sql.standardQuery(query);
			}
			catch(SQLException e)
			{
				main.getUtilLogger().logLevel(LogLevel.WARNING, "SQL> Exception occured in SQLRunnable. Class responsible: " + classname);
				e.printStackTrace();
			}
		}
	}
}
