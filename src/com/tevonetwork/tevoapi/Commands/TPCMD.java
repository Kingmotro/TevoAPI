package com.tevonetwork.tevoapi.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tevonetwork.tevoapi.API.Util.CC;
import com.tevonetwork.tevoapi.API.Util.UtilPlayer;
import com.tevonetwork.tevoapi.Core.Category;
import com.tevonetwork.tevoapi.Core.Rank;
import com.tevonetwork.tevoapi.Core.Messages.CategoryMSG;
import com.tevonetwork.tevoapi.Core.Messages.PermMSG;
import com.tevonetwork.tevoapi.Core.Travel.SendtoLocation;

public class TPCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (UtilPlayer.hasRank(p, Rank.BUILDER))
			{
				if (args.length > 0)
				{
					if (args.length == 1)
					{
						Player target = Bukkit.getPlayer(args[0]);
						if (target != null)
						{
							new SendtoLocation(p, target.getLocation());
							UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Teleporting you to " + target.getDisplayName() + CC.tnInfo + "...");
						}
						else
						{	
							for (Player targets : Bukkit.getOnlinePlayers())
							{
								if (targets.getName().toLowerCase().startsWith(args[0].toLowerCase()))
								{
									target = targets;
									break;
								}
							}
							if (target != null)
							{
								new SendtoLocation(p, target.getLocation());
								UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Teleporting you to " + target.getDisplayName() + CC.tnInfo + "...");
							}
							else
							{
								UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Invalid target player!");
							}
						}
					}
					if (args.length >= 2)
					{
						if (UtilPlayer.hasRank(p, Rank.ADMIN))
						{
							Player target1 = Bukkit.getPlayer(args[0]);
							Player target2 = Bukkit.getPlayer(args[1]);
							if ((target1 != null) && (target2 != null))
							{
								UtilPlayer.message(Category.TRAVEL, target1, CC.tnInfo + "Teleporting you to " + target2.getDisplayName() + CC.tnInfo + "...");
								new SendtoLocation(target1, target2.getLocation());
								UtilPlayer.message(Category.TRAVEL, target2, target1.getDisplayName() + CC.tnInfo + " has been teleported to you" + CC.end);
							}
							else
							{
								UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "One of the target players specified is invalid!");
							}
						}
						else
						{
							PermMSG.noPerm(sender, Rank.ADMIN);
						}
					}
				}
				else
				{
					CategoryMSG.senderArgsErr(sender, Category.TRAVEL, "/tp <player> [targetplayer]");
				}
			}
			else
			{
				PermMSG.noPerm(sender, Rank.BUILDER);
			}
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.TRAVEL);
		}
		return false;
	}
	
	

}
