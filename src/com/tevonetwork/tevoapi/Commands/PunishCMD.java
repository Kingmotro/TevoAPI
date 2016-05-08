package com.tevonetwork.tevoapi.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.API.Permissions.PermissionsHandler;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Messages.PermMSG;
import com.tevonetwork.tevoapi.Core.Punish.Punish;

public class PunishCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{	
		if (sender instanceof Player)
		{
			if (PermissionsHandler.hasRankSender(sender, Rank.MODERATOR))
			{
				if (args.length > 0)
				{
					if (args[0].equalsIgnoreCase("ban"))
					{
						if (args.length > 2)
						{
							Punish.banPlayer(args[1], sender.getName(), args[2]);
						}
						else
						{
							CategoryMSG.senderArgsErr(sender, Category.PUNISH, "/punish ban <targetPlayer> <reason>");
						}
					}
					else if (args[0].equalsIgnoreCase("tempban"))
					{
						if (args.length > 3)
						{
							String timing = "";
							for (String data : args)
							{
								if ((!data.equals(args[1])) && (!data.equals(args[0])) && (!data.equals(args[2])))
								{
									timing = timing + data + " ";
								}
							}
							Punish.tempBanPlayer(args[1], sender.getName(), args[2], timing);
						}
						else
						{
							CategoryMSG.senderArgsErr(sender, Category.PUNISH, "/punish tempban <targetPlayer> <reason> <time(d:# h:# m:#)>");
						}
					}
					else if (args[0].equalsIgnoreCase("unban"))
					{
						if (args.length > 1)
						{
							Punish.unbanPlayer(args[1], sender.getName());
						}
						else
						{
							CategoryMSG.senderArgsErr(sender, Category.PUNISH, "/punish unban <targetPlayer>");
						}
					}
					else if (args[0].equalsIgnoreCase("mute"))
					{
						if (args.length > 2)
						{
							Punish.mutePlayer(args[1], sender.getName(), args[2]);
						}
						else
						{
							CategoryMSG.senderArgsErr(sender, Category.PUNISH, "/punish mute <targetPlayer> <reason>");
						}
					}
					else if (args[0].equalsIgnoreCase("tempmute"))
					{
						if (args.length > 3)
						{
							String timing = "";
							for (String data : args)
							{
								if ((!data.equals(args[1])) && (!data.equals(args[0])) && (!data.equals(args[2])))
								{
									timing = timing + data + " ";
								}
							}
							Punish.tempMutePlayer(args[1], sender.getName(), args[2], timing);
						}
						else
						{
							CategoryMSG.senderArgsErr(sender, Category.PUNISH, "/punish tempmute <targetPlayer> <reason> <time(d:# h:# m:#)>");
						}
						
					}
					else if (args[0].equalsIgnoreCase("unmute"))
					{
						if (args.length > 1)
						{
							Punish.unmutePlayer(args[1], sender.getName());
						}
						else
						{
							CategoryMSG.senderArgsErr(sender, Category.PUNISH, "/punish unmute <targetPlayer>");
						}
					}
					else if (args[0].equalsIgnoreCase("info"))
					{
						if (args.length > 1)
						{
							Punish.punishInfo(((Player)sender), args[1]);
						}
						else
						{
							CategoryMSG.senderArgsErr(sender, Category.PUNISH, "/punish info <targetPlayer>");
						}
					}
					else
					{
						CategoryMSG.senderArgsErr(sender, Category.PUNISH, "/punish <ban/tempban/unban/mute/tempmute/unmute> <targetPlayer> [reason] [time(d:# h:# m:#)]");
					}
				}
				else
				{
					CategoryMSG.senderArgsErr(sender, Category.PUNISH, "/punish <ban/tempban/unban/mute/tempmute/unmute/info> <targetPlayer> [reason] [time(d:# h:# m:#)]");
				}
			}
			else
			{
				PermMSG.noPerm(sender, Rank.MODERATOR);
			}
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.PUNISH);
		}
		return false;
	}
	
}
