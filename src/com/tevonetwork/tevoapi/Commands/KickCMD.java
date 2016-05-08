package com.tevonetwork.tevoapi.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.tevonetwork.tevoapi.API.Permissions.PermissionsHandler;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Messages.PermMSG;
import com.tevonetwork.tevoapi.Core.Punish.Punish;


public class KickCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (PermissionsHandler.hasRankSender(sender, Rank.ADMIN))
		{
			if (args.length > 0)
			{
				if (args.length == 1)
				{
					Punish.kickPlayer(args[0]);
				}
				else
				{
					StringBuilder builder = new StringBuilder();
					for (String reason : args)
					{
						if (!reason.equals(args[0]))
						{
							builder.append(reason + " ");
						}
					}
					Punish.kickPlayer(args[0], builder.toString());
				}
			}
			else
			{
				CategoryMSG.senderArgsErr(sender, Category.SERVER, "/kick <player> [reason]");
			}
		}
		else
		{
			PermMSG.noPerm(sender, Rank.ADMIN);
		}
		return false;
	}

}
