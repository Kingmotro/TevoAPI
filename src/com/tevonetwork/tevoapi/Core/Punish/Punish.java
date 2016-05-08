package com.tevonetwork.tevoapi.Core.Punish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.tevonetwork.tevoapi.TevoAPI;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Networking.TevoNetworkMessageTask;

public class Punish {

	private static TevoAPI main =  TevoAPI.getInstance();	
	
	public static void banPlayer(String player, String actor, String reason)
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Ban");
		out.writeUTF(player);
		out.writeUTF(actor);
		out.writeUTF(reason.replace("_", " "));
		new TevoNetworkMessageTask(out.toByteArray()).runTaskAsynchronously(main);
	}
	
	public static void tempBanPlayer(String player, String actor, String reason, String timing)
	{
		String[] times = timing.split(" ");
		int mins = 0;
		int hours = 0;
		int days = 0;
		
		for (int x = 0; x < times.length; x++)
		{
			try
			{
				if (times[x].length() > 1)
				{
					if (times[x].substring(0, 2).equalsIgnoreCase("d:"))
					{
						days += Integer.parseInt(times[x].substring(2, times[x].length()));
					}
					else if (times[x].substring(0, 2).equalsIgnoreCase("h:"))
					{
						hours += Integer.parseInt(times[x].substring(2, times[x].length()));
					}
					else if (times[x].substring(0, 2).equalsIgnoreCase("m:"))
					{
						mins += Integer.parseInt(times[x].substring(2, times[x].length()));
					}
					else
					{
						Player p = Bukkit.getServer().getPlayer(actor);
						if (p != null)
						{
							p.sendMessage(CategoryMSG.getMSGPrefix(Category.PUNISH) + CC.tnError + "Invalid time format: " + CC.tnUse + "d:# h:# m:#");
						}
						return;
					}
				}
				else
				{
					Player p = Bukkit.getServer().getPlayer(actor);
					if (p != null)
					{
						p.sendMessage(CategoryMSG.getMSGPrefix(Category.PUNISH) + CC.tnError + "Invalid time format: " + CC.tnUse + "d:# h:# m:#");
					}
					return;
				}
			}
			catch (NumberFormatException e)
			{
				Player p = Bukkit.getServer().getPlayer(actor);
				if (p != null)
				{
					p.sendMessage(CategoryMSG.getMSGPrefix(Category.PUNISH) + CC.tnError + "Invalid time format: " + CC.tnUse + "d:# h:# m:#");
				}
				return;
			}
		}
		
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Tempban");
		out.writeUTF(player);
		out.writeUTF(actor);
		out.writeUTF(reason.replace("_", " "));
		out.writeInt(days);
		out.writeInt(hours);
		out.writeInt(mins);
		new TevoNetworkMessageTask(out.toByteArray()).runTaskAsynchronously(main);
	}
	
	public static void unbanPlayer(String player, String actor)
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Unban");
		out.writeUTF(player);
		out.writeUTF(actor);
		new TevoNetworkMessageTask(out.toByteArray()).runTaskAsynchronously(main);
	}
	
	public static void kickPlayer(String player)
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("KickDefault");
		out.writeUTF(player);
		new TevoNetworkMessageTask(out.toByteArray()).runTaskAsynchronously(main);
	}
	
	public static void kickPlayer(String player, String reason)
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Kick");
		out.writeUTF(player);
		out.writeUTF(reason.replace("_", " "));
		new TevoNetworkMessageTask(out.toByteArray()).runTaskAsynchronously(main);
	}
	
	public static void mutePlayer(String player, String actor, String reason)
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Mute");
		out.writeUTF(player);
		out.writeUTF(actor);
		out.writeUTF(reason.replace("_", " "));
		new TevoNetworkMessageTask(out.toByteArray()).runTaskAsynchronously(main);
	}
	
	public static void tempMutePlayer(String player, String actor, String reason, String timing)
	{
		String[] times = timing.split(" ");
		int mins = 0;
		int hours = 0;
		int days = 0;
		
		for (int x = 0; x < times.length; x++)
		{
			try
			{
				if (times[x].length() > 1)
				{
					if (times[x].substring(0, 2).equalsIgnoreCase("d:"))
					{
						days += Integer.parseInt(times[x].substring(2, times[x].length()));
					}
					else if (times[x].substring(0, 2).equalsIgnoreCase("h:"))
					{
						hours += Integer.parseInt(times[x].substring(2, times[x].length()));
					}
					else if (times[x].substring(0, 2).equalsIgnoreCase("m:"))
					{
						mins += Integer.parseInt(times[x].substring(2, times[x].length()));
					}
					else
					{
						Player p = Bukkit.getServer().getPlayer(actor);
						if (p != null)
						{
							p.sendMessage(CategoryMSG.getMSGPrefix(Category.PUNISH) + CC.tnError + "Invalid time format: " + CC.tnUse + "d:# h:# m:#");
						}
						return;
					}
				}
				else
				{
					Player p = Bukkit.getServer().getPlayer(actor);
					if (p != null)
					{
						p.sendMessage(CategoryMSG.getMSGPrefix(Category.PUNISH) + CC.tnError + "Invalid time format: " + CC.tnUse + "d:# h:# m:#");
					}
					return;
				}
			}
			catch (NumberFormatException e)
			{
				Player p = Bukkit.getServer().getPlayer(actor);
				if (p != null)
				{
					p.sendMessage(CategoryMSG.getMSGPrefix(Category.PUNISH) + CC.tnError + "Invalid time format: " + CC.tnUse + "d:# h:# m:#");
				}
				return;
			}
		}
		
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Tempmute");
		out.writeUTF(player);
		out.writeUTF(actor);
		out.writeUTF(reason.replace("_", " "));
		out.writeInt(days);
		out.writeInt(hours);
		out.writeInt(mins);
		new TevoNetworkMessageTask(out.toByteArray()).runTaskAsynchronously(main);
	}
	
	public static void unmutePlayer(String player, String actor)
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Unmute");
		out.writeUTF(player);
		out.writeUTF(actor);
		new TevoNetworkMessageTask(out.toByteArray()).runTaskAsynchronously(main);
	}
	
	public static void punishInfo(Player requester, String subject)
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("PunishInfo");
		out.writeUTF(requester.getName());
		out.writeUTF(subject);
		
		new TevoNetworkMessageTask(out.toByteArray()).runTaskAsynchronously(main);
	}
	
}
