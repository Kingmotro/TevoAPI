package com.tevonetwork.tevoapi.Commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.ChatChannel;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Messages.PermMSG;

public class ChannelCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (UtilPlayer.isStaff(p))
			{
				if (args.length == 0)
				{
					UtilPlayer.message(Category.CHAT, p, ChatColor.YELLOW + "You are currently in chat channel: " + ChatColor.GREEN + UtilPlayer.getChatChannel(p).toString().toUpperCase() + ChatColor.YELLOW + ".");
				}
				if (args.length >= 1)
				{
					if (args[0].equalsIgnoreCase("STAFF"))
					{
						UtilPlayer.setChatChannel(p, ChatChannel.STAFF);
						UtilPlayer.message(Category.CHAT, p, ChatColor.YELLOW + "Your channel is now: " + ChatColor.GREEN + "STAFF" + ChatColor.YELLOW + " This will change back to " + ChatColor.GREEN + "DEFAULT" + ChatColor.YELLOW + " when you change server!");	
					}
					else if ((args[0].equalsIgnoreCase("RESET")) || (args[0].equalsIgnoreCase("DEFAULT")))
					{
						UtilPlayer.setChatChannelDefault(p);
						UtilPlayer.message(Category.CHAT, p, ChatColor.YELLOW + "Your channel is now: " + ChatColor.GREEN + "DEFAULT" + ChatColor.YELLOW + ".");
					}
					else if (args[0].equalsIgnoreCase("GLOBAL"))
					{
						if (UtilPlayer.hasRank(p, Rank.ADMIN))
						{
							UtilPlayer.message(Category.CHAT, p, ChatColor.YELLOW + "Your channel is now: " + ChatColor.GREEN + "GLOBAL" + ChatColor.YELLOW + ".");
							UtilPlayer.setChatChannel(p, ChatChannel.GLOBAL);
						}
						else
						{
							PermMSG.noPerm(sender, Rank.ADMIN);
						}
					}
					else
					{
						List<String> channelnames = new ArrayList<String>();
						for (ChatChannel channels : ChatChannel.values())
						{
							channelnames.add(channels.toString().toUpperCase());
						}
						
						StringBuilder builder = new StringBuilder();
						
						for (Iterator<String> itr = channelnames.iterator(); itr.hasNext();)
						{
							builder.append(ChatColor.GREEN + itr.next());
							if (itr.hasNext())
							{
								builder.append(ChatColor.YELLOW + ", ");
							}
						}
						
						UtilPlayer.message(Category.CHAT, p, ChatColor.RED + "Channel does not exist! " + CC.tnInfo + "Channels: ");
						UtilPlayer.messageHeader(Category.WORLD, p, "Chat Channels");
						UtilPlayer.messageNoCategory(p, builder.toString());
						UtilPlayer.messageFooter(p);
					}
				}
			}
			else
			{
				PermMSG.noPerm(sender, Rank.MODERATOR);
			}
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.CHAT);
		}
		return true;
	}

}
