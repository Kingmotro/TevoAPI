package com.tevonetwork.tevoapi.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Chat.AntiSwearCheck;
import com.tevonetwork.tevoapi.Core.Chat.ChatManager;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;

public class MSGCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
		
			if (args.length > 1)
			{
				if (UtilPlayer.hasRank(p, Rank.CRYSTAL))
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
					if (UtilPlayer.hasRank(p, Rank.ADMIN))
					{
						ChatManager.sendPrivateChatMessage(p.getName(), p.getDisplayName(), args[0], ChatColor.translateAlternateColorCodes('&', builder.toString()), false);
					}
					else
					{
						if (AntiSwearCheck.scanMessage(builder.toString()))
						{
							ChatManager.sendPrivateChatMessage(p.getName(), p.getDisplayName(), args[0], ChatColor.translateAlternateColorCodes('&', builder.toString()), false);
						}
						else
						{
							UtilPlayer.message(Category.CHAT, p, CC.tnError + "Please don't say that!");
						}
					}
				}
				else
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
					if (AntiSwearCheck.scanMessage(builder.toString()))
					{
						ChatManager.sendPrivateChatMessage(p.getName(), p.getDisplayName(), args[0], builder.toString(), false);
					}
					else
					{
						UtilPlayer.message(Category.CHAT, p, CC.tnError + "Please don't say that!");
					}
				}
			}
			else
			{
				CategoryMSG.senderArgsErr(sender, Category.CHAT, "/msg <player> <msg>");
			}
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.CHAT);
		}
		return false;
	}

}
