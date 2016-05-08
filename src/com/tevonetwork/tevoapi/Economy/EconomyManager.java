package com.tevonetwork.tevoapi.Economy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.MySQL.SQLCallback;
import com.tevonetwork.tevoapi.API.MySQL.SQLManager;
import com.tevonetwork.tevoapi.API.MySQL.SQLRunnable;
import com.tevonetwork.tevoapi.API.Scoreboards.ScoreboardManager;
import com.tevonetwork.tevoapi.API.Util.UUIDFetcher;
import com.tevonetwork.tevoapi.Core.LogLevel;

public class EconomyManager {

	private static TevoAPI main = TevoAPI.getInstance();
	private static SQLManager sql = main.getSQLManager();
	private static String table_Tokens = "TevoTokens";
	private static String column_Balance = "Balance";
	
	private static HashMap<UUID, Integer> tokenscache = new HashMap<UUID, Integer>();
	
	
	
	public static int getTokensBal(Player p)
	{
		int balance = 0;
		if (tokenscache.containsKey(p.getUniqueId()))
		{
			balance = tokenscache.get(p.getUniqueId());
		}
		return balance;
		
	}
	
	public static void addTokens(Player p, int amount)
	{
		int currentbal = 0;
		int newbal = 0;
		if (tokenscache.containsKey(p.getUniqueId()))
		{
			currentbal = tokenscache.get(p.getUniqueId());
			newbal = currentbal + amount;
			tokenscache.put(p.getUniqueId(), newbal);
			updateValue(p.getUniqueId(), String.valueOf(newbal));
		}
		ScoreboardManager.updateTokens(p);
		
	}
	
	public static void takeTokens(Player p, int amount)
	{
		int currentbal = 0;
		int newbal = 0;
		if (tokenscache.containsKey(p.getUniqueId()))
		{
			currentbal = tokenscache.get(p.getUniqueId());
			newbal = currentbal - amount;
			if (newbal <= 0)
			{
				newbal = 0;
			}
			tokenscache.put(p.getUniqueId(), newbal);
			updateValue(p.getUniqueId(), String.valueOf(newbal));
		}
		else
		{
			currentbal = getValue(p.getUniqueId());
			newbal = currentbal - amount;
			if (newbal <= 0)
			{
				newbal = 0;
			}
			tokenscache.put(p.getUniqueId(), newbal);
			updateValue(p.getUniqueId(), String.valueOf(newbal));
		}
		ScoreboardManager.updateTokens(p);
	}
	
	public static void setTokens(Player p, int amount)
	{
		int newbal = amount;
		if (newbal <= 0)
		{
			newbal = 0;
		}
		tokenscache.put(p.getUniqueId(), newbal);
		updateValue(p.getUniqueId(), String.valueOf(newbal));
		ScoreboardManager.updateTokens(p);
		
	}
	
	public static void resetTokens(Player p)
	{
		int newbal = 0;
		tokenscache.put(p.getUniqueId(), newbal);
		updateValue(p.getUniqueId(), String.valueOf(newbal));
		ScoreboardManager.updateTokens(p);
	}
	
	public static int getTokensOffline(String playername)
	{
		int balance = 0;
		UUID uuid = null;
		try 
		{
			uuid = UUIDFetcher.getUUID(playername);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			main.getUtilLogger().logLevel(LogLevel.WARNING, "An exception occured whilst getting player UUID in class: " + EconomyManager.class.getName());
		}
		if (uuid != null)
		{
			balance = getValue(uuid);
		}
		return balance;
	}
	
	public static boolean addTokensOffline(String playername, int amount)
	{
		boolean success = false;
		int currentbal = 0;
		int newbal = 0;
		UUID uuid = null;
		try
		{
			uuid = UUIDFetcher.getUUID(playername);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			main.getUtilLogger().logLevel(LogLevel.WARNING, "Economy> An exception occured whilst getting player UUID!");
		}
		if (uuid != null)
		{
			currentbal = getValue(uuid);
			newbal = currentbal + amount;
			updateValue(uuid, String.valueOf(newbal));
			success = true;
		}
		return success;
	}
	
	public static boolean removeTokensOffline(String playername, int amount)
	{
		boolean success = false;
		int currentbal = 0;
		int newbal = 0;
		UUID uuid = null;
		try
		{
			uuid = UUIDFetcher.getUUID(playername);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			main.getUtilLogger().logLevel(LogLevel.WARNING, "Economy> An exception occured whilst getting player UUID!");
		}
		if (uuid != null)
		{
			currentbal = getValue(uuid);
			newbal = currentbal - amount;
			if (newbal <= 0)
			{
				newbal = 0;
			}
			updateValue(uuid, String.valueOf(newbal));
			success = true;
		}
		return success;
	}
	
	public static boolean setTokensOffline(String playername, int amount)
	{
		boolean success = false;
		int newbal = amount;
		UUID uuid = null;
		try
		{
			uuid = UUIDFetcher.getUUID(playername);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			main.getUtilLogger().logLevel(LogLevel.WARNING, "Economy> An exception occured whilst getting player UUID!");
		}
		if (uuid != null)
		{
			if (newbal <= 0)
			{
				newbal = 0;
			}
			updateValue(uuid, String.valueOf(newbal));
				
		}
		return success;
	}
	
	public static boolean resetTokensOffline(String playername)
	{
		boolean success = false;
		int newbal = 0;
		UUID uuid = null;
		try
		{
			uuid = UUIDFetcher.getUUID(playername);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			main.getUtilLogger().logLevel(LogLevel.WARNING, "Economy> An exception occured whilst getting player UUID!");
		}
		if (uuid != null)
		{
			updateValue(uuid, String.valueOf(newbal));
			success = true;
		}
		return success;
	}
	
	public static void removeCache(Player p)
	{
		tokenscache.remove(p.getName());
	}
	
	public static void addtoCache(final Player p)
	{
		EconomyCallback<Integer> call = new EconomyCallback<Integer>() {
			@Override
			public void execute(Integer response)
			{
				tokenscache.put(p.getUniqueId(), response);
				ScoreboardManager.updateTokens(p);
			}
		};
		runCacheCallback(getSQLUUID(p.getUniqueId()), call);
	}
	
	private static String getSQLUUID(UUID uuid)
	{
		return uuid.toString().replace("-", "");
	}
	
	private static int getValue(UUID puuid)
	{
		String uuid = getSQLUUID(puuid);
		int returnedvalue = 0;
		try
		{
			if (sql.existanceQuery("SELECT UUID FROM " + table_Tokens + " WHERE UUID = '" + uuid + "';"))
			{
				ResultSet set = sql.sqlQuery("SELECT " + column_Balance + " FROM " + table_Tokens + " WHERE UUID = '" + uuid + "';");
				if ((set.next()) && (set != null))
				{
					returnedvalue = set.getInt(1);
				}
				set.close();
			}
			else
			{
				main.getServer().getScheduler().runTaskAsynchronously(main, new SQLRunnable("INSERT INTO " + table_Tokens + "(UUID," + column_Balance + ") VALUES('" + uuid + "','0') ON DUPLICATE KEY UPDATE UUID = '" + uuid + "';", "EconomyManager"));
			}
		}
		catch(SQLException e)
		{
			main.getUtilLogger().logLevel(LogLevel.WARNING, "Economy> Failed to get value!");
			e.printStackTrace();
		}
		return returnedvalue;
	}
	
	private static void updateValue(final UUID puuid, final String value)
	{
		final String uuid = getSQLUUID(puuid);
		SQLCallback<Boolean> call = new SQLCallback<Boolean>()
		{
			@Override
			public void execute(Boolean response)
			{
				if (response)
				{
					main.getServer().getScheduler().runTaskAsynchronously(main, new SQLRunnable("UPDATE " + table_Tokens + " SET " + column_Balance + " = " + value + " WHERE UUID ='" + uuid + "';", "EconomyManager"));
				}
				else
				{
					main.getServer().getScheduler().runTaskAsynchronously(main, new SQLRunnable("INSERT INTO " + table_Tokens + "(UUID," + column_Balance + ") VALUES('" + uuid + "','" + value + "') ON DUPLICATE KEY UPDATE UUID = '" + uuid + "';", "EconomyManager"));
				}
			}
			
		};
		runCallback("SELECT UUID FROM " + table_Tokens + " WHERE UUID ='" + uuid + "';", call);
	}
	
	private static void runCacheCallback(final String uuid, final EconomyCallback<Integer> callback)
	{
		new BukkitRunnable() {
			
			@Override
			public void run()
			{
				int tokens = 0;
				try
				{
					ResultSet set = sql.sqlQuery("SELECT " + column_Balance + " FROM " + table_Tokens + " WHERE UUID = '" + uuid + "';");
					if ((set != null) && (set.next()))
					{
						tokens = set.getInt(1);
					}
					set.close();
				}
				catch(SQLException e)
				{
					main.getUtilLogger().logLevel(LogLevel.WARNING, "Economy> Invalid response from cache callback!");
					e.printStackTrace();
				}
				final Integer response = tokens;
				new BukkitRunnable() {
					@Override
					public void run()
					{
						callback.execute(response);
					}
				}.runTask(main);
			}
		}.runTaskAsynchronously(main);
	}

	private static void runCallback(final String query, final SQLCallback<Boolean> callback)
	{
		new BukkitRunnable() {
			
			@Override
			public void run()
			{
				boolean ret = false;
				try
				{
					ret = sql.existanceQuery(query);
				}
				catch(SQLException e)
				{
					main.getUtilLogger().logLevel(LogLevel.WARNING, "SQL> Sql callback failed!");
					e.printStackTrace();
				}
				final boolean callbackboolean = ret;
				new BukkitRunnable() {
					
					@Override
					public void run()
					{
						callback.execute(callbackboolean);
					}
				}.runTask(main);
			}
		}.runTaskAsynchronously(main);
	}
}
