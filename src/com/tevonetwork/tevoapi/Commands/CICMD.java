package com.tevonetwork.tevoapi.Commands;

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

public class CICMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (!UtilPlayer.hasRank(p, Rank.ADMIN))
			{
				PermMSG.noPerm(sender, Rank.ADMIN);
				return true;
			}
			UtilPlayer.clearInv(p);
			UtilPlayer.message(Category.PLAYER, p, CC.tnInfo + "Cleared your inventory!");
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.PLAYER);
		}
		return true;
	}

}
