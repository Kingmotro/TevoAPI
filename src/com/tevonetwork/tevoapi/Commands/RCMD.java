package com.tevonetwork.tevoapi.Commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Chat.ChatManager;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;

public class RCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (args.length > 0)
			{
				if (UtilPlayer.hasReplyCache(p))
				{
					if (UtilPlayer.hasRank(p, Rank.CRYSTAL))
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
						ChatManager.sendPrivateChatMessage(p.getName(), p.getDisplayName(), UtilPlayer.getReplyCache(p), ChatColor.translateAlternateColorCodes('&', builder.toString()), false);
					}
					else
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
						ChatManager.sendPrivateChatMessage(p.getName(), p.getDisplayName(), UtilPlayer.getReplyCache(p), builder.toString(), false);
					}
				}
				else
				{
					UtilPlayer.message(Category.CHAT, p, CC.tnError + "You have nobody to reply to!");
				}
			}
			else
			{
				CategoryMSG.senderArgsErr(sender, Category.CHAT, "/r <message>");
			}
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.CHAT);
		}
		return false;
	}

}
