package com.tevonetwork.tevoapi.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.API.Permissions.PermissionsHandler;
import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Messages.PermMSG;

public class SudoCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (PermissionsHandler.hasRankSender(sender, Rank.ADMIN))
		{
			if (args.length > 1)
			{
				Player subject = Bukkit.getServer().getPlayer(args[0]);
				if (subject != null)
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
					subject.chat(ChatColor.translateAlternateColorCodes('&', builder.toString()));
					return true;
				}
				else
				{
					CategoryMSG.senderMessage(sender, Category.UTILS, CC.tnError + "Invalid Player!");
				}
			}
			else
			{
				CategoryMSG.senderArgsErr(sender, Category.UTILS, "/sudo <player> <action>");
			}
		}
		else
		{
			PermMSG.noPerm(sender, Rank.ADMIN);
		}
		return false;
	}

}
