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

public class TPHereCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (UtilPlayer.hasRank(p, Rank.BUILDER))
			{
				if (args.length > 0)
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if (target != null)
					{
						new SendtoLocation(target, p.getLocation());
						UtilPlayer.message(Category.TRAVEL, p, CC.tnInfo + "Teleporting " + target.getDisplayName() + CC.tnInfo + " to you" + CC.end + "..");
						UtilPlayer.message(Category.TRAVEL, target, CC.tnInfo + "Teleporting you " + CC.tnInfo + "to " + p.getDisplayName() + CC.end + "..");
					}
					else
					{
						UtilPlayer.message(Category.TRAVEL, p, CC.tnError + "Invalid target player!");
					}
				}
				else
				{
					CategoryMSG.senderArgsErr(sender, Category.TRAVEL, "/tphere <playername>");
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
