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

public class FlyCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (UtilPlayer.hasRank(p, Rank.ADMIN))
			{
				if ((p.isFlying()) || (p.getAllowFlight()))
				{
					p.setFlying(false);
					p.setAllowFlight(false);
					UtilPlayer.message(Category.PLAYER, p, CC.tnInfo + "You " + CC.tnDisable + "disabled" + CC.tnAbility + " Flying" + CC.end);
				}
				else
				{
					p.setAllowFlight(true);
					p.setFlying(true);
					UtilPlayer.message(Category.PLAYER, p, CC.tnInfo + "You " + CC.tnEnable + "enabled" + CC.tnAbility + " Flying" + CC.end);
				}
			}
			else
			{
				PermMSG.noPerm(sender, Rank.ADMIN);
			}
		}
		else
		{
			CategoryMSG.senderMessagePlayersOnly(sender, Category.PLAYER);
		}
		return false;
	}

}
