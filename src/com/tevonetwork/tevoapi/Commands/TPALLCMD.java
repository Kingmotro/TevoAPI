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

public class TPALLCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (UtilPlayer.hasRank(p, Rank.ADMIN))
			{
				for (Player players : Bukkit.getOnlinePlayers())
				{
					players.teleport(p);
					UtilPlayer.message(Category.TRAVEL, players, CC.tnInfo + "You have been teleported to " + p.getDisplayName() + CC.end);
				}
			}
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.TRAVEL);
		}
		return false;
	}

}
