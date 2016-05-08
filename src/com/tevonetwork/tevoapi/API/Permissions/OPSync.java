package com.tevonetwork.tevoapi.API.Permissions;

import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.MySQL.SQLCallback;
import com.tevonetwork.tevoapi.API.MySQL.SQLManager;
import com.tevonetwork.tevoapi.API.MySQL.SQLRunnable;
import com.tevonetwork.tevoapi.API.Util.UUIDFetcher;
import com.tevonetwork.tevoapi.Core.LogLevel;

/**
 * OPSyncTM A simple solution to keep owners op across the network.
 * 
 * @author Thrusmyster
 *
 */
public class OPSync {

	private static SQLManager sql = TevoAPI.getInstance().getSQLManager();
	private static TevoAPI main = TevoAPI.getInstance();
	
	public static void checkOP(final Player p)
	{
		String uuid = p.getUniqueId().toString().replace("-", "");
		SQLCallback<Boolean> call = new SQLCallback<Boolean>() {

			@Override
			public void execute(Boolean response)
			{
				if (response)
				{
					if (p.isOp())
					{
						return;
					}
					p.setOp(true);
					main.getUtilLogger().logNormal("OPSync> " + p.getName() + " was opped as automatically!");
				}
				else
				{
					if (!p.isOp())
					{
						return;
					}
					p.setOp(false);
					main.getUtilLogger().logNormal("OPSync> " + p.getName() + " had unauthorised op!");
				}
			}
		};
		runCallback("SELECT UUID FROM OPS WHERE UUID = '" + uuid + "';", call);
	}
	
	public static void setOP(Player p)
	{
		p.setOp(true);
		UUID puuid = null;
		puuid = p.getUniqueId();
		if (puuid == null)
		{
			return;
		}
		main.getServer().getScheduler().runTaskAsynchronously(main, new SQLRunnable("INSERT INTO OPS(UUID) VALUES('" + puuid.toString().replace("-", "") + "') ON DUPLICATE KEY UPDATE UUID = UUID;", "OPSync"));
		main.getUtilLogger().logNormal("OPSync> " + p.getName() + " was opped.");
	}
	
	public static void setOP(String playername)
	{
		UUID puuid = null;
		puuid = UUIDFetcher.getUUID(playername);
		if (puuid == null)
		{
			return;
		}
		main.getServer().getScheduler().runTaskAsynchronously(main, new SQLRunnable("INSERT INTO OPS(UUID) VALUES('" + puuid.toString().replace("-", "") + "') ON DUPLICATE KEY UPDATE UUID = UUID;", "OPSync"));
		main.getUtilLogger().logNormal("OPSync> " + playername + " was opped (offline)!");
	}
	
	public static void removeOP(Player p)
	{
		p.setOp(false);
		final String uuid = p.getUniqueId().toString().replace("-", "");
		final String name = p.getName();
		SQLCallback<Boolean> call = new SQLCallback<Boolean>()
		{
			@Override
			public void execute(Boolean response)
			{
				if (response)
				{
					main.getServer().getScheduler().runTaskAsynchronously(main, new SQLRunnable("DELETE FROM OPS WHERE UUID = '" + uuid + "';", "OPSync"));
					main.getUtilLogger().logNormal("OPSync> " + name + " has been deopped.");
				}
			}
		};
		runCallback("SELECT UUID FROM OPS WHERE UUID = '" + uuid + "';", call);
	}
	
	public static void removeOP(final String playername)
	{
		UUID puuid = null;
		puuid = UUIDFetcher.getUUID(playername);
		if (puuid == null)
		{
			return;
		}
		final String uuid = puuid.toString().replace("-", "");
		SQLCallback<Boolean> call = new SQLCallback<Boolean>() {
			@Override
			public void execute(Boolean response)
			{
				if (response)
				{
					main.getServer().getScheduler().runTaskAsynchronously(main, new SQLRunnable("DELETE FROM OPS WHERE UUID = '" + uuid + "';", "OPSync"));
					main.getUtilLogger().logNormal("OPSync> " + playername + " has been deopped (offline).");
				}
			}
			
		};
		runCallback("SELECT UUID FROM OPS WHERE UUID = '" + puuid.toString().replace("-", "") + "';", call);
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
					main.getUtilLogger().logLevel(LogLevel.WARNING, "OPSync> SQL callback failed!");
				}
				final boolean callboolean = ret;
				new BukkitRunnable() {
					
					@Override
					public void run()
					{
						callback.execute(callboolean);
					}
				}.runTask(main);
			}
		}.runTaskAsynchronously(main);
	}
}
