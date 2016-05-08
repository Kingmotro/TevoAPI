package com.tevonetwork.tevoapi.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.API.Permissions.PermissionsHandler;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.AnnounceMSG;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Messages.PermMSG;

public class BroadcastCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (PermissionsHandler.hasRankSender(sender, Rank.ADMIN))
		{
			if (args.length > 0)
			{
				if (args[0].contains("-network"))
				{
					StringBuilder builder = new StringBuilder();
					for (int index = 1; index < args.length; index++)
					{
						if (index == 1)
						{
							builder.append(args[index]);
						}
						else
						{
							builder.append(" " + args[index]);
						}
					}
					toNetwork(ChatColor.translateAlternateColorCodes('&', builder.toString()));
				}
				else if (args[0].contains("-server"))
				{
					StringBuilder builder = new StringBuilder();
					for (int index = 1; index < args.length; index++)
					{
						if (index == 1)
						{
							builder.append(args[index]);
						}
						else
						{
							builder.append(" " + args[index]);
						}
					}
					toServer(ChatColor.translateAlternateColorCodes('&', builder.toString()));
				}
				else if (args[0].contains("-world"))
				{
					World world = Bukkit.getWorld(args[1]);
					if (world != null)
					{
						StringBuilder builder = new StringBuilder();
						for (int index = 2; index < args.length; index++)
						{
							if (index == 2)
							{
								builder.append(args[index]);
							}
							else
							{
								builder.append(" " + args[index]);
							}
						}
						toWorld(ChatColor.translateAlternateColorCodes('&', builder.toString()), world);
					}
					else
					{
						CategoryMSG.senderMessage(sender, Category.BROADCAST, CC.tnError + "Invalid world!");
					}
				}
				else
				{
					if (sender instanceof Player)
					{
						Player p = (Player)sender;
						if (args.length > 1)
						{
							StringBuilder builder = new StringBuilder();
							for (int index = 0; index < args.length; index++)
							{
								if (index == 0)
								{
									builder.append(args[index]);
								}
								else
								{
									builder.append(" " + args[index]);
								}
							}
							toWorld(ChatColor.translateAlternateColorCodes('&', builder.toString()), p.getWorld());
						}
						else
						{
							sendArgError(sender);
						}
					}
					else if (sender instanceof ConsoleCommandSender)
					{
						if (args.length > 1)
						{
							StringBuilder builder = new StringBuilder();
							for (int index = 0; index < args.length; index++)
							{
								if (index == 0)
								{
									builder.append(args[index]);
								}
								else
								{
									builder.append(" " + args[index]);
								}
							}
							toServer(ChatColor.translateAlternateColorCodes('&', builder.toString()));
						}
						else
						{
							sendArgError(sender);
						}
					}
					else
					{
						sendArgError(sender);
					}
				}
			}
			else
			{
				sendArgError(sender);
			}
		}
		else
		{
			PermMSG.noPerm(sender, Rank.ADMIN);
		}
		return true;
	}

	private void sendArgError(CommandSender sender)
	{
		CategoryMSG.senderMessage(sender, Category.BROADCAST, CC.tnError + "Not enough Arguments!" + CC.tnInfo + " Usage: " + CC.tnUse + "/broadcast [-network/-world/-server] [worldname] <msg>");
	}
	
	private void toNetwork(String msg)
	{
		AnnounceMSG.toNetwork(msg);
	}
	
	private void toWorld(String msg, World w)
	{
		AnnounceMSG.toWorld(w, msg);
	}
	
	private void toServer(String msg)
	{
		AnnounceMSG.toServer(msg);
	}
}


